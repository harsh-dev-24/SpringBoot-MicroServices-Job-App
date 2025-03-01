package com.harsh.jobms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.harsh.jobms.clients.CompanyClient;
import com.harsh.jobms.clients.ReviewClient;
import com.harsh.jobms.dto.JobCompanyReviewDTO;
import com.harsh.jobms.external.Company;
import com.harsh.jobms.external.Review;
import com.harsh.jobms.mapper.JobMapper;
import com.harsh.jobms.model.Job;
import com.harsh.jobms.repository.JobRepository;
import com.harsh.jobms.service.JobService;

//used open feign client for interservice communication
@Service
@Primary
public class JobServiceImpl2 implements JobService {

	private JobRepository jobRepo;

	private CompanyClient companyClient;

	private ReviewClient reviewClient;

	public JobServiceImpl2(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
		this.jobRepo = jobRepository;
		this.companyClient = companyClient;
		this.reviewClient = reviewClient;
	}

	private List<Job> jobs = new ArrayList<>(List.of(
			new Job("Java Developer", "Java SpringBoot Developer with minimum 3 YOE", 800000, 1200000, "Mumbai",
					"active"),
			new Job("Python Developer", "Python Django Developer with minimum 2 YOE", 100000, 1300000, "Pune",
					"active"),
			new Job("Frontend Developer", "Frontend React Developer with minimum 2 YOE", 700000, 1100000, "Bangalore",
					"active"),
			new Job("Software Engineer (Fresher)", "Software Engineer with good coding skills", 400000, 700000, "Noida",
					"active")));

//	private List<Job> jobs = new ArrayList<>();

//	private Integer nextId = 5;

	@Override
	public boolean loadJobs() {
		if (jobs.size() == 0) {
			return false;
		}
		jobRepo.saveAll(jobs);
		return true;

	}

	@Override
	public List<JobCompanyReviewDTO> findAllJobs() {
		List<Job> jobs = jobRepo.findAll();
		return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	// Implementing FeignClient to communicate with company service
	private JobCompanyReviewDTO convertToDTO(Job job) {
		Company company = companyClient.getCompany(job.getCompanyId());
		List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
		JobCompanyReviewDTO jobCompanyReviewDTO = JobMapper.mapToJobCompanyDTO(job, company, reviews);
		return jobCompanyReviewDTO;
	}

	@Override
	public JobCompanyReviewDTO findJobById(Integer id) {
		Job job = jobRepo.findById(id).orElse(null);
		if (job != null) {
			JobCompanyReviewDTO jobCompanyDTO = convertToDTO(job);
			return jobCompanyDTO;
		}
		return null;

	}

	@Override
	public boolean createJob(Job job) {
		if (job != null) {
			jobRepo.save(job);
			return true;
		}
		return false;

	}

	@Override
	public Job updateJob(Job job, Integer id) {

		if (job == null) {
			return null;
		} else {
			Job fetchJob = jobRepo.findById(id).orElse(null);
			if (fetchJob != null) {
				fetchJob.setDescription(job.getDescription());
				fetchJob.setTitle(job.getTitle());
				fetchJob.setLocation(job.getLocation());
				fetchJob.setStatus(job.getStatus());
				fetchJob.setMinSalary(job.getMinSalary());
				fetchJob.setMaxSalary(job.getMaxSalary());
				fetchJob.setCompany(job.getCompanyId());

				jobRepo.save(fetchJob);
			}

			return fetchJob;
		}

	}

	@Override
	public Job deleteJob(Integer id) {
		Job job = jobRepo.findById(id).orElse(null);
		if (job != null) {
			jobRepo.delete(job);
		}
		return job;

	}

	@Override
	public List<Job> searchJob(String keyword) {
		List<Job> jobs = jobRepo.findByKeyword(keyword);
		if (jobs.size() > 0) {
			return jobs;
		}
		return null;
	}

	@Override
	public List<Job> findJobsByStatus(String status) {
		List<Job> jobs = jobRepo.findByStatus(status);
		if (jobs.size() > 0) {
			return jobs;
		}
		return null;
	}

}
