package com.heroku.match.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BackgroundJob {
	private List<Matched> matched;
	
	private String jobid;

	private int size;
	
	public BackgroundJob() {
		matched = new ArrayList<Matched>();
		jobid = UUID.randomUUID().toString();
		size = 0;
	}
	public BackgroundJob(String jobid) {
		this.jobid = jobid;
	}
	
	public List<Matched> getMatched() {
		return matched;
	}

	public void setMatched(List<Matched> matched) {
		this.matched = matched;
		this.size = this.matched.size();
	}

	public String getJobid() {
		return jobid;
	}
	public int size() {
		return size;
	}
	

}

