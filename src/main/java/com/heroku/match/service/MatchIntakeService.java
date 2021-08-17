package com.heroku.match.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.JobScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heroku.match.domain.IntakeLead;
import com.heroku.match.domain.Lead;
import com.heroku.match.domain.custom.IHowMany;
import com.heroku.match.domain.custom.ILeadSimilarity;
import com.heroku.match.exception.BadResourceException;
import com.heroku.match.exception.ResourceNotFoundException;
import com.heroku.match.model.BackgroundJob;
import com.heroku.match.model.Matched;
import com.heroku.match.repository.IntakeLeadRepository;
import com.heroku.match.repository.LeadRepository;
import com.heroku.match.repository.MatchedRepository;

@Service
public class MatchIntakeService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IntakeLeadRepository intakeLeadRepository;

	@Autowired
	private LeadRepository leadRepository;
	
	@Autowired
	private MatchedRepository matchedRepository;
	
	@Autowired
    private JobScheduler jobScheduler;

	public List<Matched> matchAll(Long start) throws BadResourceException, ResourceNotFoundException {
		Long end = start + 100;
		List<Matched> matches = new ArrayList<Matched>();
		List<IntakeLead> intakeLeads = intakeLeadRepository.findByIdBetween(start, end);
		
		for (IntakeLead lead: intakeLeads) {
			List<Matched> matchedRecords = match(lead, null);
			
			if (matchedRecords.size() == 0)
				matches.add(addLead(lead));
		}
		
		return matches;
	}
	
	public BackgroundJob analyzeAll(Long start, Long end, boolean save) throws BadResourceException, ResourceNotFoundException {
		BackgroundJob job = new BackgroundJob();
		List<Matched> matches = new ArrayList<Matched>();

		String jobid = job.getJobid();
		
		if (end < start) end = start + 100;
		
		if (end - start <= 100) {
			List<IntakeLead> intakeLeads = intakeLeadRepository.findByIdBetween(start, end);
			
			for (IntakeLead lead: intakeLeads) 
				matches.addAll(analyze(lead, save, job.getJobid()));
			
			job.setMatched(matches);
		} else 
			jobScheduler.enqueue(() -> this.jobAnalyzeAll(jobid));
		
		return job;
	}

	public BackgroundJob analyzeAll() throws BadResourceException, ResourceNotFoundException {
		BackgroundJob job = new BackgroundJob();
		String jobid = job.getJobid();
		
		jobScheduler.enqueue(() -> this.jobAnalyzeAll(jobid));
		log.info("Job successfully enqueued -> [" + jobid + "]");
		
		return job;
	}

	@Job(name = "Analyzing all Leads for JobId[%0]", retries = 1)
	public void jobAnalyzeAll(String jobid) {
		boolean keepSearching = true;
		long start = 0;
		
		while (keepSearching) {
			IHowMany howMany = intakeLeadRepository.findHowManyRecordsToProcess(start);
			start = howMany.getMinid();
			long end = start + 100;
			
			log.info("JobId[" + jobid + "] - Starting from id:" + start + ", ending in id:" + end + " --- HowMany: " +  howMany);
			
			if (howMany.getHowmany() == 0) break;

			List<IntakeLead> intakeLeads = intakeLeadRepository.findByIdBetween(start, end);
			List<Matched> matches = new ArrayList<Matched>();
			
			for (IntakeLead lead: intakeLeads) 
				matches.addAll(analyze(lead, true, jobid));

			start = end + 1;
			if (start > howMany.getMaxid()) {
				log.info("JobId[" + jobid + "] - FInished!!! - No more records to find, max id processed was [" + howMany.getMaxid() + "]");
				keepSearching = false;
			}
		}
	
	}
	
	public List<Matched> matchByIdAndSave(Long id)  throws BadResourceException, ResourceNotFoundException {
		
		Optional<IntakeLead> find = intakeLeadRepository.findById(id);
		
		log.debug("Is Present? " + find.isPresent());
		
		if (!find.isPresent())
			throw new ResourceNotFoundException("Id [" + id + "] not found in the Intake Lead table!");

		IntakeLead lead = find.get();

		List<Matched> matches = match(lead, null);
		
		if (matches.size() == 0)
			matches.add(addLead(lead));
		
		
		return matches;
	}
	
	private Matched addLead(IntakeLead i) throws BadResourceException, ResourceNotFoundException {
		
		Lead lead = new Lead(i);
		Matched match = new Matched();

		try {
			Lead saved = leadRepository.save(lead);

			addMatchedRecordFromSavedLead(match, saved);
		} catch (Exception s) {
			throw new BadResourceException("Failed to save Lead",s);
		}
		return match;
	}
	private void addMatchedRecordFromSavedLead(Matched match, Lead saved) {
		match.setMatched(false);
		match.setMatchId(saved.getId());
		match.setMatchedValue("");
		match.setName(saved.getName());
		match.setEntityMatched("salesforce.lead");
		match.setEntitySearch("");
		match.setValue(saved.getExternalId());
		match.setColumn("external_id__c");
		match.setMatchedBy("NEW");
		match.setPercentage(0D);
		match.setData(toJson(saved));
	}
	
	public List<Matched> match(IntakeLead intakeLead, String job) {
		List<Matched> allMatches = new ArrayList<Matched>();
		
		if (intakeLead.getExternalId() != null) {
			List<Matched> extid = matchLeadByExternalId(intakeLead.getExternalId(), job);
			allMatches.addAll(extid);
			return allMatches;
		}
		
		if (intakeLead.getDoordashId() != null) {
			List<Matched> ddid = matchLeadByDDId(intakeLead.getDoordashId(), job);
			allMatches.addAll(ddid);
			return allMatches;
		}
		
		if (intakeLead.getName() != null) {
			List<Matched> matchLeadByName = matchLeadByName((intakeLead.getName()), job);
			allMatches.addAll(matchLeadByName);
		}
		return allMatches;
	}

	public List<Matched> analyze(IntakeLead intakeLead, boolean save, String job) {
		List<Matched> allMatches = new ArrayList<Matched>();
		
		if (intakeLead.getExternalId() != null) {
			List<Matched> extid = matchLeadByExternalId(intakeLead.getExternalId(), job);
			allMatches.addAll(extid);
		}
		
		if (intakeLead.getDoordashId() != null) {
			List<Matched> ddid = matchLeadByDDId(intakeLead.getDoordashId(), job);
			allMatches.addAll(ddid);
		}
		
		if (intakeLead.getName() != null) {
			List<Matched> matchLeadByName = matchLeadByName((intakeLead.getName()), job);
			allMatches.addAll(matchLeadByName);
		}
		
		if (save)
			matchedRepository.saveAll(allMatches);
		
		return allMatches;
	}
	
	private List<Matched>matchLeadByDDId(String ddId, String job) {

		List<Matched> matches = new ArrayList<Matched>();
		List<Lead> similarMatches = leadRepository.findByDoordashId(ddId);
		
//		log.debug("Found (" + similarMatches.size() + ") by DoordashID: [" +  ddId  + "]");
		
		for (Lead matchedLead: similarMatches) {
			Matched ddMatch = new Matched();
			ddMatch.setMatched(true);
			ddMatch.setMatchId(matchedLead.getId());
			ddMatch.setMatchedValue(matchedLead.getDoordashId());
			ddMatch.setName(matchedLead.getName());
			ddMatch.setEntityMatched("salesforce.lead");
			ddMatch.setEntitySearch("IntakeLead");
			ddMatch.setValue(ddId);
			ddMatch.setColumn("doordash_id__c");
			ddMatch.setMatchedBy("DOORDASH ID");
			ddMatch.setJob(job);
			
			ddMatch.setPercentage(1D);
			ddMatch.setData(toJson(matchedLead));
			matches.add(ddMatch);

		}
		return matches;
	}
	private List<Matched> matchLeadByExternalId(String externalId, String job) {

		List<Matched> matches = new ArrayList<Matched>();
		List<Lead> extIdMatches = leadRepository.findByExternalId(externalId);
		
//		log.debug("Found (" + extIdMatches.size() + ") by ExternalID: [" +  externalId  + "]");
		
		for (Lead matchedLead: extIdMatches) {
			Matched extMatch = new Matched();
			extMatch.setMatched(true);
			extMatch.setMatchId(matchedLead.getId());
			extMatch.setMatchedValue(matchedLead.getExternalId());
			extMatch.setName(matchedLead.getName());
			extMatch.setEntityMatched("salesforce.lead");
			extMatch.setEntitySearch("IntakeLead");
			extMatch.setValue(externalId);
			extMatch.setColumn("external_id__c");
			extMatch.setMatchedBy("EXTERNAL ID");
			extMatch.setJob(job);
			
			extMatch.setPercentage(1D);
			extMatch.setData(toJson(matchedLead));
			matches.add(extMatch);

		}
		return matches;
	}
	public boolean matchLead(IntakeLead intakeLead) {
		
		return (matchLeadByName(intakeLead.getName(), null).size() > 0);
	}

	public List<Matched> matchLeadByName(String name, String job) {
		List<Matched> matches = new ArrayList<Matched>();
		
		//Match name with lead.companyname by similarity
		List<Lead> similarMatches = leadRepository.findNamesBySimilarity(name);
			
//		log.debug("Found (" + similarMatches.size() + ") for name: [" +  name  + "]");
		
		for (Lead matchedLead: similarMatches) {
			//log.debug("Object found ... " + toJson(matchedLead));

			Matched nameMatch = new Matched();
			nameMatch.setMatched(true);
			nameMatch.setMatchId(matchedLead.getId());
			nameMatch.setMatchedValue(matchedLead.getCompany());
			nameMatch.setName(matchedLead.getName());
			nameMatch.setEntityMatched("salesforce.lead");
			nameMatch.setEntitySearch("IntakeLead");
			nameMatch.setValue(name);
			nameMatch.setColumn("COMPANY");
			nameMatch.setMatchedBy("NAME > .6");
			nameMatch.setJob(job);
			ILeadSimilarity percentage = leadRepository.findPercentage(matchedLead.getId(), name);

			nameMatch.setPercentage(percentage.getPercentage().doubleValue());
			nameMatch.setData(toJson(matchedLead));
			
			matches.add(nameMatch);
		}
		
		return matches;
	}
	
	public List<Matched> matchLeadByAddress(String name) {
		List<Matched> matches = new ArrayList<Matched>();
		
		//Match name with lead.companyname by similarity
		List<Lead> similarMatches = leadRepository.findNamesBySimilarity(name);
			
//		log.debug("Found (" + similarMatches.size() + ") for name: [" +  name  + "]");
		
		
		for (Lead matchedLead: similarMatches) {
			//log.debug("Object found ... " + toJson(matchedLead));

			Matched nameMatch = new Matched();
			nameMatch.setMatched(true);
			nameMatch.setMatchId(matchedLead.getId());
			nameMatch.setMatchedValue(matchedLead.getCompany());
			
			nameMatch.setEntityMatched("salesforce.lead");
			nameMatch.setEntitySearch("IntakeLead");
			nameMatch.setValue(name);
			nameMatch.setColumn("COMPANY");
			nameMatch.setMatchedBy("NAME > .6");
			
			ILeadSimilarity percentage = leadRepository.findPercentage(matchedLead.getId(), name);
			log.debug("Percentage found: " + percentage.getPercentage());

			nameMatch.setPercentage(percentage.getPercentage().doubleValue());
			nameMatch.setData(toJson(matchedLead));
			
			matches.add(nameMatch);
		}
		
		return matches;
	}
	public String toJson(Object o) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		String ret = "";
		try {
			ret = objectMapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			if (log.isDebugEnabled())
				e.printStackTrace();
		}
		
		return ret;
	}
	

}
