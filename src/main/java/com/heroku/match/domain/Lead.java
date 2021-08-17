package com.heroku.match.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the lead database table.
 * 
 */
@Entity
@Table(name="lead", schema="test")
public class Lead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double annualrevenue;

	@Column(name="average_rating__c")
	private Double averageRating;

	private String city;

	private String company;

	@Column(name="competitor__c")
	private String competitor;

	@Column(name="convertedaccount__external_id__c")
	private String convertedaccountExternalId;

	private String convertedaccountid;

	@Column(name="convertedcontact__external_id__c")
	private String convertedcontactExternalId;

	private String convertedcontactid;

	@Temporal(TemporalType.DATE)
	private Date converteddate;

	@Column(name="convertedopportunity__external_id__c")
	private String convertedopportunityExternalId;

	private String convertedopportunityid;

	private String country;

	private String createdbyid;

	private Timestamp createddate;

	@Temporal(TemporalType.DATE)
	@Column(name="createddate__c")
	private Date leadCreatedDate;

	@Column(name="cuisines__c")
	private String cuisines;

	private String description;

	private Boolean donotcall;

	@Column(name="doordash_id__c")
	private String doordashId;

	private String email;

	private Timestamp emailbounceddate;

	private String emailbouncedreason;

	@Column(name="external_id__c")
	private String externalId;

	private String firstname;

	private String geocodeaccuracy;

	private Boolean hasoptedoutofemail;

	private Boolean hasoptedoutoffax;

	private String industry;

	private Boolean isconverted;

	private Boolean isdeleted;

	private Boolean islocked;

	private Boolean isunreadbyowner;

	@Temporal(TemporalType.DATE)
	private Date lastactivitydate;

	private String lastmodifiedbyid;

	private Timestamp lastmodifieddate;

	private String lastname;

	private Timestamp lastreferenceddate;

	@Temporal(TemporalType.DATE)
	private Date lasttransferdate;

	private Timestamp lastvieweddate;

	private Double latitude;

	@Column(name="lead_number__c")
	private String leadNumber;

	private String leadsource;

	private Double longitude;

	private String mobilephone;

	private String name;

	private Integer numberofemployees;

	@Column(name="opt_in_for_sms__c")
	private Boolean optInForSms;

	private String phone;

	private String photourl;

	@Column(name="pi__score__c")
	private Double piScore;

	private String postalcode;

	private String rating;

	private String recordtypeid;

	@Column(name="region__c")
	private String region;

	@Column(name="reviews_count__c")
	private Double reviewsCount;

	private String salutation;

	private String sfid;

	private String state;

	private String status;

	private String street;

	private Timestamp systemmodstamp;

	private String title;

	private String website;

	public Lead() {
	}

	public Lead(IntakeLead i) {
		super();
		this.annualrevenue = i.getAnnualrevenue();
		this.averageRating = i.getAverageRating();
		this.city = i.getCity();
		this.company = i.getCompany();
		this.competitor = i.getCompetitor();
		this.country = i.getCountry();
		this.leadCreatedDate = i.getCreateddate();
		this.cuisines = i.getCuisines();
		this.description = i.getDescription();
		this.doordashId = i.getDoordashId();
		this.email = i.getEmail();
		this.externalId = i.getExternalId();
		this.firstname = i.getFirstname();
		this.industry = i.getIndustry();
		this.isconverted = false;
		this.isdeleted = false;
		this.islocked = false;
		this.lastname = i.getLastname();
		this.latitude = i.getLatitude();
		this.leadNumber = i.getLeadNumber();
		this.leadsource = i.getLeadsource();
		this.longitude = i.getLongitude();
		this.name = i.getName();
		this.numberofemployees = i.getNumberofemployees();
		this.phone = i.getPhone();
		this.photourl = i.getPhotourl();
		this.postalcode = i.getPostalcode();
		this.rating = i.getRating();
		this.region = i.getRegion();
		this.reviewsCount = i.getReviewsCount();
		this.state = i.getState();
		this.status = i.getStatus();
		this.street = i.getStreet();
		this.title = i.getTitle();
		this.website = i.getWebsite();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAnnualrevenue() {
		return this.annualrevenue;
	}

	public void setAnnualrevenue(Double annualrevenue) {
		this.annualrevenue = annualrevenue;
	}

	public Double getAverageRating() {
		return this.averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
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

	public String getCompetitor() {
		return this.competitor;
	}

	public void setCompetitor(String competitor) {
		this.competitor = competitor;
	}

	public String getConvertedaccountExternalId() {
		return this.convertedaccountExternalId;
	}

	public void setConvertedaccountExternalId(String convertedaccountExternalId) {
		this.convertedaccountExternalId = convertedaccountExternalId;
	}

	public String getConvertedaccountid() {
		return this.convertedaccountid;
	}

	public void setConvertedaccountid(String convertedaccountid) {
		this.convertedaccountid = convertedaccountid;
	}

	public String getConvertedcontactExternalId() {
		return this.convertedcontactExternalId;
	}

	public void setConvertedcontactExternalId(String convertedcontactExternalId) {
		this.convertedcontactExternalId = convertedcontactExternalId;
	}

	public String getConvertedcontactid() {
		return this.convertedcontactid;
	}

	public void setConvertedcontactid(String convertedcontactid) {
		this.convertedcontactid = convertedcontactid;
	}

	public Date getConverteddate() {
		return this.converteddate;
	}

	public void setConverteddate(Date converteddate) {
		this.converteddate = converteddate;
	}

	public String getConvertedopportunityExternalId() {
		return this.convertedopportunityExternalId;
	}

	public void setConvertedopportunityExternalId(String convertedopportunityExternalId) {
		this.convertedopportunityExternalId = convertedopportunityExternalId;
	}

	public String getConvertedopportunityid() {
		return this.convertedopportunityid;
	}

	public void setConvertedopportunityid(String convertedopportunityid) {
		this.convertedopportunityid = convertedopportunityid;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreatedbyid() {
		return this.createdbyid;
	}

	public void setCreatedbyid(String createdbyid) {
		this.createdbyid = createdbyid;
	}

	public Timestamp getCreateddate() {
		return this.createddate;
	}

	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}

	public Date getLeadCreatedDatee() {
		return this.leadCreatedDate;
	}

	public void setLeadCreatedDate(Date leadCreatedDate) {
		this.leadCreatedDate = leadCreatedDate;
	}

	public String getCuisines() {
		return this.cuisines;
	}

	public void setCuisines(String cuisines) {
		this.cuisines = cuisines;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDonotcall() {
		return this.donotcall;
	}

	public void setDonotcall(Boolean donotcall) {
		this.donotcall = donotcall;
	}

	public String getDoordashId() {
		return this.doordashId;
	}

	public void setDoordashId(String doordashId) {
		this.doordashId = doordashId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getEmailbounceddate() {
		return this.emailbounceddate;
	}

	public void setEmailbounceddate(Timestamp emailbounceddate) {
		this.emailbounceddate = emailbounceddate;
	}

	public String getEmailbouncedreason() {
		return this.emailbouncedreason;
	}

	public void setEmailbouncedreason(String emailbouncedreason) {
		this.emailbouncedreason = emailbouncedreason;
	}

	public String getExternalId() {
		return this.externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getGeocodeaccuracy() {
		return this.geocodeaccuracy;
	}

	public void setGeocodeaccuracy(String geocodeaccuracy) {
		this.geocodeaccuracy = geocodeaccuracy;
	}

	public Boolean getHasoptedoutofemail() {
		return this.hasoptedoutofemail;
	}

	public void setHasoptedoutofemail(Boolean hasoptedoutofemail) {
		this.hasoptedoutofemail = hasoptedoutofemail;
	}

	public Boolean getHasoptedoutoffax() {
		return this.hasoptedoutoffax;
	}

	public void setHasoptedoutoffax(Boolean hasoptedoutoffax) {
		this.hasoptedoutoffax = hasoptedoutoffax;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Boolean getIsconverted() {
		return this.isconverted;
	}

	public void setIsconverted(Boolean isconverted) {
		this.isconverted = isconverted;
	}

	public Boolean getIsdeleted() {
		return this.isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public Boolean getIslocked() {
		return this.islocked;
	}

	public void setIslocked(Boolean islocked) {
		this.islocked = islocked;
	}

	public Boolean getIsunreadbyowner() {
		return this.isunreadbyowner;
	}

	public void setIsunreadbyowner(Boolean isunreadbyowner) {
		this.isunreadbyowner = isunreadbyowner;
	}

	public Date getLastactivitydate() {
		return this.lastactivitydate;
	}

	public void setLastactivitydate(Date lastactivitydate) {
		this.lastactivitydate = lastactivitydate;
	}

	public String getLastmodifiedbyid() {
		return this.lastmodifiedbyid;
	}

	public void setLastmodifiedbyid(String lastmodifiedbyid) {
		this.lastmodifiedbyid = lastmodifiedbyid;
	}

	public Timestamp getLastmodifieddate() {
		return this.lastmodifieddate;
	}

	public void setLastmodifieddate(Timestamp lastmodifieddate) {
		this.lastmodifieddate = lastmodifieddate;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Timestamp getLastreferenceddate() {
		return this.lastreferenceddate;
	}

	public void setLastreferenceddate(Timestamp lastreferenceddate) {
		this.lastreferenceddate = lastreferenceddate;
	}

	public Date getLasttransferdate() {
		return this.lasttransferdate;
	}

	public void setLasttransferdate(Date lasttransferdate) {
		this.lasttransferdate = lasttransferdate;
	}

	public Timestamp getLastvieweddate() {
		return this.lastvieweddate;
	}

	public void setLastvieweddate(Timestamp lastvieweddate) {
		this.lastvieweddate = lastvieweddate;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getLeadNumber() {
		return this.leadNumber;
	}

	public void setLeadNumber(String leadNumber) {
		this.leadNumber = leadNumber;
	}

	public String getLeadsource() {
		return this.leadsource;
	}

	public void setLeadsource(String leadsource) {
		this.leadsource = leadsource;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
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

	public Boolean getOptInForSms() {
		return this.optInForSms;
	}

	public void setOptInForSms(Boolean optInForSms) {
		this.optInForSms = optInForSms;
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

	public Double getPiScore() {
		return this.piScore;
	}

	public void setPiScore(Double piScore) {
		this.piScore = piScore;
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

	public String getRecordtypeid() {
		return this.recordtypeid;
	}

	public void setRecordtypeid(String recordtypeid) {
		this.recordtypeid = recordtypeid;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Double getReviewsCount() {
		return this.reviewsCount;
	}

	public void setReviewsCount(Double reviewsCount) {
		this.reviewsCount = reviewsCount;
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

	public Timestamp getSystemmodstamp() {
		return this.systemmodstamp;
	}

	public void setSystemmodstamp(Timestamp systemmodstamp) {
		this.systemmodstamp = systemmodstamp;
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