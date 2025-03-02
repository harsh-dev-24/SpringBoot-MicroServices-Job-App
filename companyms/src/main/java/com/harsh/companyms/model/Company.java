package com.harsh.companyms.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	@Column(precision = 2, scale = 1) // Max 2 digits, 1 after decimal
	private BigDecimal averageRating;
//	private List<>

	/*
	 * @JsonIgnore
	 * 
	 * @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE) private
	 * List<Job> jobs;
	 */

	/*
	 * // reviews
	 * 
	 * @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE) private
	 * List<Review> reviews;
	 */

	public Company() {

	}

	public Company(String name, String description) {

		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(BigDecimal averageRating) {
		this.averageRating = averageRating;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
