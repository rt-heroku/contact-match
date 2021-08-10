package com.heroku.match.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the lead database table.
 * 
 */
@Entity
@Table(name="lead", schema="intake")
@NamedQuery(name="Lead.findAll", query="SELECT l FROM Lead l")
public class IntakeLead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private double annualrevenue;

	@Column(name="average_rating__c")
	private double averageRatingC;

	private String city;

	private String company;

	@Column(name="competitor__c")
	private String competitorC;

	private String country;

	private Timestamp createddate;

	@Column(name="cuisines__c")
	private String cuisinesC;

	private String description;

	@Column(name="doordash_id__c")
	private String doordashIdC;

	private String email;

	@Column(name="external_id__c")
	private String externalIdC;

	private String firstname;

	private String industry;

	private String lastname;

	private double latitude;

	@Column(name="lead_number__c")
	private String leadNumberC;

	private String leadsource;

	private double longitude;

	private String mobilephone;

	private String name;

	private Integer numberofemployees;

	private String phone;

	private String photourl;

	private String postalcode;

	private String rating;

	@Column(name="region__c")
	private String regionC;

	@Column(name="reviews_count__c")
	private double reviewsCountC;

	private String salutation;

	private String sfid;

	private String state;

	private String status;

	private String street;

	private String title;

	private String website;

	public IntakeLead() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAnnualrevenue() {
		return this.annualrevenue;
	}

	public void setAnnualrevenue(double annualrevenue) {
		this.annualrevenue = annualrevenue;
	}

	public double getAverageRatingC() {
		return this.averageRatingC;
	}

	public void setAverageRatingC(double averageRatingC) {
		this.averageRatingC = averageRatingC;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompetitorC() {
		return this.competitorC;
	}

	public void setCompetitorC(String competitorC) {
		this.competitorC = competitorC;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getCreateddate() {
		return this.createddate;
	}

	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}

	public String getCuisinesC() {
		return this.cuisinesC;
	}

	public void setCuisinesC(String cuisinesC) {
		this.cuisinesC = cuisinesC;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDoordashIdC() {
		return this.doordashIdC;
	}

	public void setDoordashIdC(String doordashIdC) {
		this.doordashIdC = doordashIdC;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExternalIdC() {
		return this.externalIdC;
	}

	public void setExternalIdC(String externalIdC) {
		this.externalIdC = externalIdC;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getLeadNumberC() {
		return this.leadNumberC;
	}

	public void setLeadNumberC(String leadNumberC) {
		this.leadNumberC = leadNumberC;
	}

	public String getLeadsource() {
		return this.leadsource;
	}

	public void setLeadsource(String leadsource) {
		this.leadsource = leadsource;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberofemployees() {
		return this.numberofemployees;
	}

	public void setNumberofemployees(Integer numberofemployees) {
		this.numberofemployees = numberofemployees;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhotourl() {
		return this.photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}

	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getRegionC() {
		return this.regionC;
	}

	public void setRegionC(String regionC) {
		this.regionC = regionC;
	}

	public double getReviewsCountC() {
		return this.reviewsCountC;
	}

	public void setReviewsCountC(double reviewsCountC) {
		this.reviewsCountC = reviewsCountC;
	}

	public String getSalutation() {
		return this.salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getSfid() {
		return this.sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}