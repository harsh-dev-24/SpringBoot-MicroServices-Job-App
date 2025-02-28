package com.harsh.jobms.dto;

import com.harsh.jobms.external.Company;
import com.harsh.jobms.model.Job;

public class JobCompanyDTO {

	private Job job;
	private Company company;

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
