package com.heroku.match.domain.custom;

public class LeadSimilarity{
		
		private Long id;
		private String company;
		private Float percentage;
		
		public LeadSimilarity(Long id, String company, Float percentage) {
			super();
			this.id = id;
			this.company = company;
			this.percentage = percentage;
		}
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public Double getPercentage() {
			return percentage.doubleValue();
		}
		public void setPercentage(Float percentage) {
			this.percentage = percentage;
		}
}
