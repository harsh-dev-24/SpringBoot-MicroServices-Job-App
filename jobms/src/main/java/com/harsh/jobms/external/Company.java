package com.harsh.jobms.external;

public class Company {

	private Integer id;
	private String name;
	private String description;

	public Company() {

	}
	public Company(Object value) {
		
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
