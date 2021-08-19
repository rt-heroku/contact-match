package com.heroku.match.service;

import java.util.ArrayList;
import java.util.List;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.context.JobContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.heroku.match.domain.IntakeLead;
import com.heroku.match.domain.custom.IHowMany;
import com.heroku.match.exception.JobException;
import com.heroku.match.model.Matched;
import com.heroku.match.repository.IntakeLeadRepository;
import com.heroku.match.repository.LeadRepository;
import com.heroku.match.repository.MatchedRepository;
import com.heroku.match.utils.MatchUtils;

public class JobMatchIntake {

}
