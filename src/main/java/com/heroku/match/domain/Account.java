package com.heroku.match.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@Table(name="account", schema="salesforce")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String accountnumber;

	private String accountsource;

	private Double annualrevenue;

	private String billingcity;

	private String billingcountry;

	private String billinggeocodeaccuracy;

	private Double billinglatitude;

	private Double billinglongitude;

	private String billingpostalcode;

	private String billingstate;

	private String billingstreet;

	private Timestamp createddate;

	private String description;

	@Column(name="external_id__c")
	private String externalId;

	private String industry;

	private Boolean isbuyer;

	private Boolean isdeleted;

	private Boolean islocked;

	private Boolean ispartner;

	@Temporal(TemporalType.DATE)
	private Date lastactivitydate;

	private String lastmodifiedbyid;

	private Timestamp lastmodifieddate;

	private Timestamp lastvieweddate;

	private String name;

	private Integer numberofemployees;

	private String ownerid;

	private String ownership;

	private String phone;

	private String sfid;

	private String shippingcity;

	private String shippingcountry;

	private String shippinggeocodeaccuracy;

	private Double shippinglatitude;

	private Double shippinglongitude;

	private String shippingpostalcode;

	private String shippingstate;

	private String shippingstreet;

	private String site;

	private Timestamp systemmodstamp;

	private String tickersymbol;

	private String type;

	private String website;

	public Account() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountnumber() {
		return this.accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getAccountsource() {
		return this.accountsource;
	}

	public void setAccountsource(String accountsource) {
		this.accountsource = accountsource;
	}

	public Double getAnnualrevenue() {
		return this.annualrevenue;
	}

	public void setAnnualrevenue(Double annualrevenue) {
		this.annualrevenue = annualrevenue;
	}

	public String getBillingcity() {
		return this.billingcity;
	}

	public void setBillingcity(String billingcity) {
		this.billingcity = billingcity;
	}

	public String getBillingcountry() {
		return this.billingcountry;
	}

	public void setBillingcountry(String billingcountry) {
		this.billingcountry = billingcountry;
	}

	public String getBillinggeocodeaccuracy() {
		return this.billinggeocodeaccuracy;
	}

	public void setBillinggeocodeaccuracy(String billinggeocodeaccuracy) {
		this.billinggeocodeaccuracy = billinggeocodeaccuracy;
	}

	public Double getBillinglatitude() {
		return this.billinglatitude;
	}

	public void setBillinglatitude(Double billinglatitude) {
		this.billinglatitude = billinglatitude;
	}

	public Double getBillinglongitude() {
		return this.billinglongitude;
	}

	public void setBillinglongitude(Double billinglongitude) {
		this.billinglongitude = billinglongitude;
	}

	public String getBillingpostalcode() {
		return this.billingpostalcode;
	}

	public void setBillingpostalcode(String billingpostalcode) {
		this.billingpostalcode = billingpostalcode;
	}

	public String getBillingstate() {
		return this.billingstate;
	}

	public void setBillingstate(String billingstate) {
		this.billingstate = billingstate;
	}

	public String getBillingstreet() {
		return this.billingstreet;
	}

	public void setBillingstreet(String billingstreet) {
		this.billingstreet = billingstreet;
	}

	public Timestamp getCreateddate() {
		return this.createddate;
	}

	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExternalId() {
		return this.externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Boolean getIsbuyer() {
		return this.isbuyer;
	}

	public void setIsbuyer(Boolean isbuyer) {
		this.isbuyer = isbuyer;
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

	public Boolean getIspartner() {
		return this.ispartner;
	}

	public void setIspartner(Boolean ispartner) {
		this.ispartner = ispartner;
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

	public Timestamp getLastvieweddate() {
		return this.lastvieweddate;
	}

	public void setLastvieweddate(Timestamp lastvieweddate) {
		this.lastvieweddate = lastvieweddate;
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

	public String getOwnerid() {
		return this.ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	public String getOwnership() {
		return this.ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSfid() {
		return this.sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getShippingcity() {
		return this.shippingcity;
	}

	public void setShippingcity(String shippingcity) {
		this.shippingcity = shippingcity;
	}

	public String getShippingcountry() {
		return this.shippingcountry;
	}

	public void setShippingcountry(String shippingcountry) {
		this.shippingcountry = shippingcountry;
	}

	public String getShippinggeocodeaccuracy() {
		return this.shippinggeocodeaccuracy;
	}

	public void setShippinggeocodeaccuracy(String shippinggeocodeaccuracy) {
		this.shippinggeocodeaccuracy = shippinggeocodeaccuracy;
	}

	public Double getShippinglatitude() {
		return this.shippinglatitude;
	}

	public void setShippinglatitude(Double shippinglatitude) {
		this.shippinglatitude = shippinglatitude;
	}

	public Double getShippinglongitude() {
		return this.shippinglongitude;
	}

	public void setShippinglongitude(Double shippinglongitude) {
		this.shippinglongitude = shippinglongitude;
	}

	public String getShippingpostalcode() {
		return this.shippingpostalcode;
	}

	public void setShippingpostalcode(String shippingpostalcode) {
		this.shippingpostalcode = shippingpostalcode;
	}

	public String getShippingstate() {
		return this.shippingstate;
	}

	public void setShippingstate(String shippingstate) {
		this.shippingstate = shippingstate;
	}

	public String getShippingstreet() {
		return this.shippingstreet;
	}

	public void setShippingstreet(String shippingstreet) {
		this.shippingstreet = shippingstreet;
	}

	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Timestamp getSystemmodstamp() {
		return this.systemmodstamp;
	}

	public void setSystemmodstamp(Timestamp systemmodstamp) {
		this.systemmodstamp = systemmodstamp;
	}

	public String getTickersymbol() {
		return this.tickersymbol;
	}

	public void setTickersymbol(String tickersymbol) {
		this.tickersymbol = tickersymbol;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}