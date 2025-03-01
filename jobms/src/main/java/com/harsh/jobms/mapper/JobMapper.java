package com.harsh.jobms.mapper;

import java.util.List;

import com.harsh.jobms.dto.JobCompanyReviewDTO;
import com.harsh.jobms.external.Company;
import com.harsh.jobms.external.Review;
import com.harsh.jobms.model.Job;

public class JobMapper {

	public static JobCompanyReviewDTO mapToJobCompanyDTO(Job job, Company company, List<Review> reviews) {

		JobCompanyReviewDTO jobCompanyDTO = new JobCompanyReviewDTO();
		jobCompanyDTO.setId(job.getId());
		jobCompanyDTO.setTitle(job.getTitle());
		jobCompanyDTO.setDescription(job.getDescription());
		jobCompanyDTO.setMinSalary(job.getMinSalary());
		jobCompanyDTO.setMaxSalary(job.getMaxSalary());
		jobCompanyDTO.setLocation(job.getLocation());
		jobCompanyDTO.setStatus(job.getStatus());
		jobCompanyDTO.setCompany(company);
		jobCompanyDTO.setReviews(reviews);
		
		return jobCompanyDTO;
	}

}
