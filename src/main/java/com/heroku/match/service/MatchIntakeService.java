package com.heroku.match.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jobrunr.jobs.JobId;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.scheduling.JobScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heroku.match.domain.IntakeLead;
import com.heroku.match.domain.Lead;
import com.heroku.match.domain.custom.IHowMany;
import com.heroku.match.domain.custom.ILeadSimilarity;
import com.heroku.match.exception.JobException;
import com.heroku.match.model.BackgroundJob;
import com.heroku.match.model.Matched;
import com.heroku.match.repository.IntakeLeadRepository;
import com.heroku.match.repository.LeadRepository;
import com.heroku.match.repository.MatchedRepository;
import com.heroku.match.utils.MatchUtils;

@Service
public class MatchIntakeService {
	
	private static final String JOB_WEB_CALL = "WEB CALL";

	private static final String ENTITY_SEARCH = "IntakeLead";

	private static final String MATCHED_LEAD_TABLE = "salesforce.lead";

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IntakeLeadRepository intakeLeadRepository;

	@Autowired
	private LeadRepository leadRepository;
	
	@Autowired
	private MatchedRepository matchedRepository;

	
	@Autowired
    private JobScheduler jobScheduler;

	/*
	 * Background job launchers
	 */
	public BackgroundJob analyzeAll() throws JobException {
		BackgroundJob job = new BackgroundJob();
		JobId enqueue = jobScheduler.enqueue(job.getUuid(),() -> this.jobAnalyzeAll(JobContext.Null));
		String msg = "Job successfully enqueued -> [" + enqueue.asUUID().toString() + "]";
		MatchUtils.log(msg);
		job.setMessage(msg);
		return job;
	}
	public BackgroundJob matchAll() throws JobException {
		BackgroundJob job = new BackgroundJob();
		JobId enqueue = jobScheduler.enqueue(job.getUuid(),() -> this.jobMatcheAll(JobContext.Null));
		String msg = "Job [Match all leads] successfully enqueued -> [" + enqueue.asUUID().toString() + "]";
		MatchUtils.log(msg);
		job.setMessage(msg);
		return job;
	}
	
	/*
	 * Background Jobs
	 */
	@Job(name = "Match all Intake Leads with Salesforce Leads", retries = 1)
	public void jobMatcheAll(JobContext jobContext) throws JobException{
		boolean keepSearching = true;
		long start = 0;
		
		try {
			List<Matched> matches = new ArrayList<Matched>();
			MatchUtils.log(jobContext, "Starting Matching all Leads Job [" + jobContext.getJobId() + "] - " + jobContext.getJobName());
					
			while (keepSearching) {
				IHowMany howMany = intakeLeadRepository.findHowManyRecordsToProcess(start);
				start = howMany.getMinid();
				long end = start + 100;
				
				MatchUtils.log(jobContext,"JobId[" + jobContext.getJobId() + "] - Starting from id:" + start + ", ending in id:" + end + " --- Total Count: " +  howMany.getHowmany());
				
				if (howMany.getHowmany() == 0) break;
				
				List<IntakeLead> intakeLeads = intakeLeadRepository.findByIdBetween(start, end);
				
				for (IntakeLead lead: intakeLeads) {
					List<Matched> matchedRecords = match(lead, jobContext.getJobId().toString());
					
					if (matchedRecords.size() == 0) {
						Matched newLead = addLead(lead, jobContext.getJobId().toString());
						matchedRepository.save(newLead);
						matches.add(newLead);
					}
				}
				
				start = end + 1;
				if (start > howMany.getMaxid()) {
					MatchUtils.log(jobContext,"JobId[" + jobContext.getJobId() + "] - FInished!!! - No more records to process, max id processed was [" + howMany.getMaxid() + "]");
					keepSearching = false;
				}
			}
			MatchUtils.log(jobContext, "JobId[" + jobContext.getJobId() + "] - Records processed and added to the table [" + matches.size() + "]");
			MatchUtils.debugMatchedList("JobId[" + jobContext.getJobId().toString() + "]: ", matches);
		}
		catch(Exception e) {
			MatchUtils.error(jobContext,"JobId[" + jobContext.getJobId() + "]: " + e.getMessage());
			throw new JobException(e);
		}
		
	}
	
	@Job(name = "Analyzing all Intake Leads with Salesforce Leads", retries = 1)
	public void jobAnalyzeAll(JobContext jobContext) throws JobException{
		boolean keepSearching = true;
		long start = 0;
		try {
			MatchUtils.log(jobContext, "Starting Analyzing all leads Job [" + jobContext.getJobId() + "] - " + jobContext.getJobName());
			
			while (keepSearching) {
				IHowMany howMany = intakeLeadRepository.findHowManyRecordsToProcess(start);
				start = howMany.getMinid();
				long end = start + 100;
				
				MatchUtils.log(jobContext, "JobId[" + jobContext.getJobId() + "] - Starting from id:" + start + ", ending in id:" + end + " --- Total Count: " +  howMany.getHowmany());
				
				if (howMany.getHowmany() == 0) break;
	
				List<IntakeLead> intakeLeads = intakeLeadRepository.findByIdBetween(start, end);
				List<Matched> matches = new ArrayList<Matched>();
				
				for (IntakeLead lead: intakeLeads) 
					matches.addAll(analyze(lead, jobContext.getJobId().toString()));
	
				start = end + 1;
				if (start > howMany.getMaxid()) {
					MatchUtils.log(jobContext, "JobId[" + jobContext.getJobId() + "] - FInished!!! - No more records to find, max id processed was [" + howMany.getMaxid() + "]");
					keepSearching = false;
				}
			}
		
		}
		catch(Exception e) {
			MatchUtils.log(jobContext, "JobId[" + jobContext.getJobId() + "]: " + e.getMessage());
			throw new JobException(e);
		}
	}
/*
 * 
 */
	public List<Matched> matchByIdAndSave(Long id)  throws JobException {
		
		Optional<IntakeLead> lead = intakeLeadRepository.findById(id);
		
		if (!lead.isPresent())
			throw new JobException("Id [" + id + "] not found in the Intake Lead table!");

		List<Matched> matches = match(lead.get(), JOB_WEB_CALL);
		
		if (matches.size() == 0)
			matches.add(addLead(lead.get(),JOB_WEB_CALL));
		
		return matches;
	}
	public List<Matched> match(IntakeLead intakeLead, String job)throws JobException {
		List<Matched> allMatches = new ArrayList<Matched>();
		
		if (intakeLead.getExternalId() != null) {
			allMatches.addAll(matchLeadByExternalId(intakeLead.getExternalId(), job));
			return allMatches;
		}
		
		if (intakeLead.getDoordashId() != null) {
			allMatches.addAll(matchLeadByDDId(intakeLead.getDoordashId(), job));
			return allMatches;
		}
		
		if (intakeLead.getName() != null)
			allMatches.addAll(matchLeadByCompanyName((intakeLead.getName()), job));
		
		return allMatches;
	}

	public List<Matched> analyze(IntakeLead intakeLead, String job) throws JobException {
		List<Matched> allMatches = new ArrayList<Matched>();
		
		if (intakeLead.getExternalId() != null)
			allMatches.addAll(matchLeadByExternalId(intakeLead.getExternalId(), job));
		
		if (intakeLead.getDoordashId() != null) 
			allMatches.addAll(matchLeadByDDId(intakeLead.getDoordashId(), job));
		
		if (intakeLead.getName() != null) 
			allMatches.addAll(matchLeadByCompanyName((intakeLead.getName()), job));
		
		matchedRepository.saveAll(allMatches);
		
		return allMatches;
	}
	
	private List<Matched> matchLeadByDDId(String ddId, String job) {

		return processLeads(
				ddId, job, 
				"doordash_id__c", "DOORDASH ID", 
				leadRepository.findByDoordashId(ddId)
			);
	}

	private List<Matched> matchLeadByExternalId(String externalId, String job) {
		
		return processLeads(
				externalId, job, 
				"external_id__c", "EXTERNAL ID", 
				leadRepository.findByExternalId(externalId)
			);
	}

	public List<Matched> matchLeadByCompanyName(String name, String job) throws JobException {
		List<Matched> matches = new ArrayList<Matched>();
		List<Lead> similarMatches = leadRepository.findNamesBySimilarity(name);
		
		for (Lead matchedLead: similarMatches) {
			Matched nameMatch = leadToMatched(name, job, matchedLead, "COMPANY","NAME > .6");
			ILeadSimilarity percentage = leadRepository.findPercentage(matchedLead.getId(), name);
			nameMatch.setPercentage(percentage.getPercentage().doubleValue());
			matches.add(nameMatch);
		}
		
		return matches;
	}

	private List<Matched> processLeads(String id, String job, String column, String matchedBy, List<Lead> found) {
		List<Matched> matches = new ArrayList<Matched>();
		for (Lead lead: found)
			matches.add(
					leadToMatched(
							id, 
							job, 
							lead, 
							column, 
							matchedBy
							)
					);
		MatchUtils.debugMatchedList("Found (" + found.size() + ") by " + matchedBy + ": [" +  id  + "]", matches);
		return matches;
	}

	private Matched leadToMatched(String value, String job, Lead matchedLead, String matchColumn, String matchedBy) {
		return new Matched(value, job, matchedLead, matchColumn, matchedBy, MATCHED_LEAD_TABLE ,ENTITY_SEARCH);
	}
	
	public boolean matchLead(IntakeLead intakeLead) throws JobException{
		
		return (matchLeadByCompanyName(intakeLead.getName(), null).size() > 0);
	}
	
	private Matched addLead(IntakeLead i, String job) throws JobException {
		try {
			return newMatchedRecordFromSavedLead(
					leadRepository.save(new Lead(i)), 
					job
				);
		} catch (Exception s) {
			throw new JobException("Failed to save Lead", s);
		}
	}
	private Matched newMatchedRecordFromSavedLead(Lead saved, String job) {
		return new Matched(false , saved.getExternalId(), job, saved, "external_id__c", "NEW", MATCHED_LEAD_TABLE, "");
	}
	
	
	
	

}
