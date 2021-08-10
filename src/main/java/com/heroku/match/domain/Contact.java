package com.heroku.match.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the contact database table.
 * 
 */
@Entity
@Table(name="contact", schema="salesforce")
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="account__external_id__c")
	private String accountExternalId;

	private String accountid;

	private String createdbyid;

	private Timestamp createddate;

	private String department;

	private String description;

	private Boolean donotcall;

	private String email;

	private Timestamp emailbounceddate;

	private String emailbouncedreason;

	@Column(name="external_id__c")
	private String externalId;

	private String firstname;

	private Boolean hasoptedoutofemail;

	private Boolean isdeleted;

	private Boolean isemailbounced;

	private Boolean islocked;

	private Boolean ispersonaccount;

	@Temporal(TemporalType.DATE)
	private Date lastactivitydate;

	private Timestamp lastcurequestdate;

	private Timestamp lastcuupdatedate;

	private String lastmodifiedbyid;

	private Timestamp lastmodifieddate;

	private String lastname;

	private Timestamp lastreferenceddate;

	private Timestamp lastvieweddate;

	private String leadsource;

	private String mailingcity;

	private String mailingcountry;

	private String mailinggeocodeaccuracy;

	private Double mailinglatitude;

	private Double mailinglongitude;

	private String mailingpostalcode;

	private String mailingstate;

	private String mailingstreet;

	private String mobilephone;

	private String name;

	private String ownerid;

	private String phone;

	private String photourl;

	@Column(name="pi__campaign__c")
	private String piCampaign;

	@Column(name="pi__conversion_date__c")
	private Timestamp piConversionDate;

	@Column(name="pi__grade__c")
	private String piGrade;

	@Column(name="pi__last_activity__c")
	private Timestamp piLastActivity;

	@Column(name="pi__pardot_last_scored_at__c")
	private Timestamp piPardotLastScoredAt;

	@Column(name="pi__score__c")
	private Double piScore;

	private String recordtypeid;

	@Column(name="reportsto__external_id__c")
	private String reportstoExternalId;

	private String reportstoid;

	private String sfid;

	private Timestamp systemmodstamp;

	private String title;

	public Contact() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountExternalId() {
		return this.accountExternalId;
	}

	public void setAccountExternalId(String accountExternalId) {
		this.accountExternalId = accountExternalId;
	}

	public String getAccountid() {
		return this.accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
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

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public Boolean getHasoptedoutofemail() {
		return this.hasoptedoutofemail;
	}

	public void setHasoptedoutofemail(Boolean hasoptedoutofemail) {
		this.hasoptedoutofemail = hasoptedoutofemail;
	}

	public Boolean getIsdeleted() {
		return this.isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public Boolean getIsemailbounced() {
		return this.isemailbounced;
	}

	public void setIsemailbounced(Boolean isemailbounced) {
		this.isemailbounced = isemailbounced;
	}

	public Boolean getIslocked() {
		return this.islocked;
	}

	public void setIslocked(Boolean islocked) {
		this.islocked = islocked;
	}

	public Boolean getIspersonaccount() {
		return this.ispersonaccount;
	}

	public void setIspersonaccount(Boolean ispersonaccount) {
		this.ispersonaccount = ispersonaccount;
	}

	public Date getLastactivitydate() {
		return this.lastactivitydate;
	}

	public void setLastactivitydate(Date lastactivitydate) {
		this.lastactivitydate = lastactivitydate;
	}

	public Timestamp getLastcurequestdate() {
		return this.lastcurequestdate;
	}

	public void setLastcurequestdate(Timestamp lastcurequestdate) {
		this.lastcurequestdate = lastcurequestdate;
	}

	public Timestamp getLastcuupdatedate() {
		return this.lastcuupdatedate;
	}

	public void setLastcuupdatedate(Timestamp lastcuupdatedate) {
		this.lastcuupdatedate = lastcuupdatedate;
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

	public Timestamp getLastvieweddate() {
		return this.lastvieweddate;
	}

	public void setLastvieweddate(Timestamp lastvieweddate) {
		this.lastvieweddate = lastvieweddate;
	}

	public String getLeadsource() {
		return this.leadsource;
	}

	public void setLeadsource(String leadsource) {
		this.leadsource = leadsource;
	}

	public String getMailingcity() {
		return this.mailingcity;
	}

	public void setMailingcity(String mailingcity) {
		this.mailingcity = mailingcity;
	}

	public String getMailingcountry() {
		return this.mailingcountry;
	}

	public void setMailingcountry(String mailingcountry) {
		this.mailingcountry = mailingcountry;
	}

	public String getMailinggeocodeaccuracy() {
		return this.mailinggeocodeaccuracy;
	}

	public void setMailinggeocodeaccuracy(String mailinggeocodeaccuracy) {
		this.mailinggeocodeaccuracy = mailinggeocodeaccuracy;
	}

	public Double getMailinglatitude() {
		return this.mailinglatitude;
	}

	public void setMailinglatitude(Double mailinglatitude) {
		this.mailinglatitude = mailinglatitude;
	}

	public Double getMailinglongitude() {
		return this.mailinglongitude;
	}

	public void setMailinglongitude(Double mailinglongitude) {
		this.mailinglongitude = mailinglongitude;
	}

	public String getMailingpostalcode() {
		return this.mailingpostalcode;
	}

	public void setMailingpostalcode(String mailingpostalcode) {
		this.mailingpostalcode = mailingpostalcode;
	}

	public String getMailingstate() {
		return this.mailingstate;
	}

	public void setMailingstate(String mailingstate) {
		this.mailingstate = mailingstate;
	}

	public String getMailingstreet() {
		return this.mailingstreet;
	}

	public void setMailingstreet(String mailingstreet) {
		this.mailingstreet = mailingstreet;
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

	public String getOwnerid() {
		return this.ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
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

	public String getPiCampaign() {
		return this.piCampaign;
	}

	public void setPiCampaign(String piCampaign) {
		this.piCampaign = piCampaign;
	}

	public Timestamp getPiConversionDate() {
		return this.piConversionDate;
	}

	public void setPiConversionDate(Timestamp piConversionDate) {
		this.piConversionDate = piConversionDate;
	}

	public String getPiGrade() {
		return this.piGrade;
	}

	public void setPiGrade(String piGrade) {
		this.piGrade = piGrade;
	}

	public Timestamp getPiLastActivity() {
		return this.piLastActivity;
	}

	public void setPiLastActivity(Timestamp piLastActivity) {
		this.piLastActivity = piLastActivity;
	}

	public Timestamp getPiPardotLastScoredAt() {
		return this.piPardotLastScoredAt;
	}

	public void setPiPardotLastScoredAt(Timestamp piPardotLastScoredAt) {
		this.piPardotLastScoredAt = piPardotLastScoredAt;
	}

	public Double getPiScore() {
		return this.piScore;
	}

	public void setPiScore(Double piScore) {
		this.piScore = piScore;
	}

	public String getRecordtypeid() {
		return this.recordtypeid;
	}

	public void setRecordtypeid(String recordtypeid) {
		this.recordtypeid = recordtypeid;
	}

	public String getReportstoExternalId() {
		return this.reportstoExternalId;
	}

	public void setReportstoExternalId(String reportstoExternalId) {
		this.reportstoExternalId = reportstoExternalId;
	}

	public String getReportstoid() {
		return this.reportstoid;
	}

	public void setReportstoid(String reportstoid) {
		this.reportstoid = reportstoid;
	}

	public String getSfid() {
		return this.sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
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

}