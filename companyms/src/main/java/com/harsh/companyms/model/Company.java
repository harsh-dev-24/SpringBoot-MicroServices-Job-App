package com.harsh.companyms.model;

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

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
