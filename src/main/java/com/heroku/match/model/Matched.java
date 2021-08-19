package com.heroku.match.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.heroku.match.domain.Lead;
import com.heroku.match.utils.MatchUtils;

@Entity
@Table(name="matched", schema="intake")
public class Matched {
	
	private boolean matched;
	private String name;
	@Column(name="matched_by")
	private String matchedBy;
	private String value;
	@Column(name="matched_value")
	private String matchedValue;
	private Double percentage;
	@Column(name="entity_field")
	private String column;
	@Column(name="entity_matched")
	private String entityMatched;
	@Column(name="entity_search")
	private String entitySearch;
	private String data;
	
	private String job;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="matched_id")
	private Long matchId;
	
	public Matched() {
		super();
	}
	
	public Matched(String value, String job, Lead lead, String matchColumn, String matchedBy, String matchedTable, String searchTable) {
		this( true,  value,  job,  lead,  matchColumn,  matchedBy,  matchedTable,  searchTable);
	}

	public Matched(boolean matched, String value, String job, Lead lead, String matchColumn, String matchedBy, String matchedTable, String searchTable) {
		super();
		this.setMatched(matched);
		this.setMatchId(lead.getId());
		this.setMatchedValue(lead.getDoordashId());
		this.setName(lead.getName());
		this.setEntityMatched(matchedTable);
		this.setEntitySearch(searchTable);
		this.setValue(value);
		this.setColumn(matchColumn);
		this.setMatchedBy(matchedBy);
		this.setJob(job);
		
		this.setPercentage(1D);
		this.setData(MatchUtils.toJson(lead));
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMatchedValue() {
		return matchedValue;
	}
	public void setMatchedValue(String matchedValue) {
		this.matchedValue = matchedValue;
	}
	public boolean isMatched() {
		return matched;
	}
	public void setMatched(boolean matched) {
		this.matched = matched;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMatchedBy() {
		return matchedBy;
	}
	public void setMatchedBy(String matchedBy) {
		this.matchedBy = matchedBy;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public Long getMatchId() {
		return matchId;
	}
	public void setMatchId(Long matchid) {
		this.matchId = matchid;
	}
	public String getEntityMatched() {
		return entityMatched;
	}
	public void setEntityMatched(String entityMatched) {
		this.entityMatched = entityMatched;
	}
	public String getEntitySearch() {
		return entitySearch;
	}
	public void setEntitySearch(String entitySearch) {
		this.entitySearch = entitySearch;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Matched{" +
				"matched:" + this.matched + "," +
				"name:" + this.name + "," +
				"matchedBy:" + this.matchedBy + "," +
				"value:" + this.value + "," +
				"matchedValue:" + this.matchedValue + "," +
				"percentage:" + this.percentage + "," +
				"column:" + this.column + "," +
				"entityMatched:" + this.entityMatched + "," +
				"entitySearch:" + this.entitySearch + "," +
				"";
	}
	
}
