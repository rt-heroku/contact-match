package com.heroku.match.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the incomingleads database table.
 * 
 */
@Entity
@Table(name="incomingleads", schema="files")
public class IncomingLead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Column(name="average_rating")
	private Double averageRating;

	private String byoc;

	private String city;

	private String competitor;

	private String country;

	private String cuisines;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Temporal(TemporalType.DATE)
	@Column(name="date_last_crawled")
	private Date dateLastCrawled;

	@Column(name="dd_id")
	private String ddId;

	private Boolean delivery;

	@Column(name="delivery_fee")
	private Double deliveryFee;

	@Column(name="df_original")
	private Double dfOriginal;

	@Column(name="df_sale")
	private Double dfSale;

	@Temporal(TemporalType.DATE)
	@Column(name="first_date")
	private Date firstDate;

	@Column(name="group_orders")
	private Boolean groupOrders;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="is_partner")
	private Boolean isPartner;

	private Double latitude;

	private Double longitude;

	private String name;

	@Column(name="name_clean")
	private String nameClean;

	@Column(name="on_caviar")
	private Boolean onCaviar;

	@Column(name="phone_number")
	private String phoneNumber;

	private Boolean pickup;

	@Column(name="postal_code")
	private String postalCode;

	@Column(name="reviews_count")
	private Double reviewsCount;

	@Column(name="reviews_count_displayed")
	private Double reviewsCountDisplayed;

	private String state;

	private String street;

	@Column(name="subcription_eligible")
	private Boolean subcriptionEligible;

	@Column(name="unique_id")
	private String uniqueId;

	private String vertical;

	public IncomingLead() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAverageRating() {
		return this.averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	public String getByoc() {
		return this.byoc;
	}

	public void setByoc(String byoc) {
		this.byoc = byoc;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompetitor() {
		return this.competitor;
	}

	public void setCompetitor(String competitor) {
		this.competitor = competitor;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCuisines() {
		return this.cuisines;
	}

	public void setCuisines(String cuisines) {
		this.cuisines = cuisines;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDateLastCrawled() {
		return this.dateLastCrawled;
	}

	public void setDateLastCrawled(Date dateLastCrawled) {
		this.dateLastCrawled = dateLastCrawled;
	}

	public String getDdId() {
		return this.ddId;
	}

	public void setDdId(String ddId) {
		this.ddId = ddId;
	}

	public Boolean getDelivery() {
		return this.delivery;
	}

	public void setDelivery(Boolean delivery) {
		this.delivery = delivery;
	}

	public Double getDeliveryFee() {
		return this.deliveryFee;
	}

	public void setDeliveryFee(Double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public Double getDfOriginal() {
		return this.dfOriginal;
	}

	public void setDfOriginal(Double dfOriginal) {
		this.dfOriginal = dfOriginal;
	}

	public Double getDfSale() {
		return this.dfSale;
	}

	public void setDfSale(Double dfSale) {
		this.dfSale = dfSale;
	}

	public Date getFirstDate() {
		return this.firstDate;
	}

	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}

	public Boolean getGroupOrders() {
		return this.groupOrders;
	}

	public void setGroupOrders(Boolean groupOrders) {
		this.groupOrders = groupOrders;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsPartner() {
		return this.isPartner;
	}

	public void setIsPartner(Boolean isPartner) {
		this.isPartner = isPartner;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameClean() {
		return this.nameClean;
	}

	public void setNameClean(String nameClean) {
		this.nameClean = nameClean;
	}

	public Boolean getOnCaviar() {
		return this.onCaviar;
	}

	public void setOnCaviar(Boolean onCaviar) {
		this.onCaviar = onCaviar;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getPickup() {
		return this.pickup;
	}

	public void setPickup(Boolean pickup) {
		this.pickup = pickup;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Double getReviewsCount() {
		return this.reviewsCount;
	}

	public void setReviewsCount(Double reviewsCount) {
		this.reviewsCount = reviewsCount;
	}

	public Double getReviewsCountDisplayed() {
		return this.reviewsCountDisplayed;
	}

	public void setReviewsCountDisplayed(Double reviewsCountDisplayed) {
		this.reviewsCountDisplayed = reviewsCountDisplayed;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Boolean getSubcriptionEligible() {
		return this.subcriptionEligible;
	}

	public void setSubcriptionEligible(Boolean subcriptionEligible) {
		this.subcriptionEligible = subcriptionEligible;
	}

	public String getUniqueId() {
		return this.uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getVertical() {
		return this.vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

}