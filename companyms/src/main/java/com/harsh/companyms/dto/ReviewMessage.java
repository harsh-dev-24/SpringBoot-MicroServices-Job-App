package com.harsh.companyms.dto;

public class ReviewMessage {

	private Integer id;
	private String title;
	private String description;
	private double rating;
	private Integer companyId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "ReviewMessage [id=" + id + ", title=" + title + ", description=" + description + ", rating=" + rating
				+ ", companyId=" + companyId + "]";
	}
	
	

}

