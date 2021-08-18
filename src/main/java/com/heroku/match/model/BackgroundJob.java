package com.heroku.match.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BackgroundJob {
	private List<Matched> matched;
	
	private String jobid;
	private UUID uuid;
	private int size;
	private String message;
	
	
	public BackgroundJob() {
		this.matched = new ArrayList<Matched>();
		this.uuid = UUID.randomUUID();
		this.jobid = getUuid().toString();
		this.size = 0;
	}
	public BackgroundJob(String jobid) {
		this.matched = new ArrayList<Matched>();
		this.jobid = jobid;
		this.uuid = UUID.fromString(jobid);
		this.size = 0;
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
	public UUID getUuid() {
		return uuid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}

