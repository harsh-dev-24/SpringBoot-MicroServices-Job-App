package com.harsh.jobms.mapper;

import com.harsh.jobms.dto.JobCompanyDTO;
import com.harsh.jobms.external.Company;
import com.harsh.jobms.model.Job;

public class JobMapper {

	public static JobCompanyDTO mapToJobCompanyDTO(Job job, Company company) {

		JobCompanyDTO jobCompanyDTO = new JobCompanyDTO();
		jobCompanyDTO.setId(job.getId());
		jobCompanyDTO.setTitle(job.getTitle());
		jobCompanyDTO.setDescription(job.getDescription());
		jobCompanyDTO.setMinSalary(job.getMinSalary());
		jobCompanyDTO.setMaxSalary(job.getMaxSalary());
		jobCompanyDTO.setLocation(job.getLocation());
		jobCompanyDTO.setStatus(job.getStatus());
		jobCompanyDTO.setCompany(company);
		
		return jobCompanyDTO;
	}

}
