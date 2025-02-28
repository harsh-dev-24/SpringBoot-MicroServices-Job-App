package com.harsh.jobms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.harsh.jobms.dto.JobCompanyDTO;
import com.harsh.jobms.dto.Pair;
import com.harsh.jobms.external.Company;
import com.harsh.jobms.mapper.JobMapper;
import com.harsh.jobms.model.Job;
import com.harsh.jobms.repository.JobRepository;
import com.harsh.jobms.service.JobService;

@Service
public class JobServiceImpl implements JobService {

	private JobRepository jobRepo;

	// Load Balanced rest template
	@Autowired
	private RestTemplate restTemplate;

	public JobServiceImpl(JobRepository jobRepository) {
		this.jobRepo = jobRepository;
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
	public List<JobCompanyDTO> findAllJobs() {

		List<Job> jobs = jobRepo.findAll();
//		List<JobCompanyDTO> jobCompanyDTOs = new ArrayList<JobCompanyDTO>();

//		RestTemplate restTemplate = new RestTemplate();

		return jobs.stream().map(j -> this.convertToDTO(j, restTemplate)).collect(Collectors.toList());

		// Example usage of Rest Template
		/*
		 * RestTemplate restTemplate = new RestTemplate(); Company company =
		 * restTemplate.getForObject("http://localhost:8081/companies/id/1",
		 * Company.class); System.out.println("COMPANY :: " + company);
		 */

//		return jobs;
//		return jobRepo.findAll();

	}

	private JobCompanyDTO convertToDTO(Job job, RestTemplate template) {

//		Company company = template.getForObject("http://localhost:8081/companies/id/" + job.getCompanyId(),Company.class);
		Company company = template.getForObject("http://COMPANY-SERVICE:8081/companies/id/" + job.getCompanyId(),
				Company.class);

		JobCompanyDTO jobCompanyDTO = JobMapper.mapToJobCompanyDTO(job, company);
		return jobCompanyDTO;
	}

	@Override
	public JobCompanyDTO findJobById(Integer id) {
		Job job = jobRepo.findById(id).orElse(null);
		if (job != null) {
			RestTemplate restTemplate = new RestTemplate();
			JobCompanyDTO jobCompanyDTO = convertToDTO(job, restTemplate);
			return jobCompanyDTO;
		}
		return null;

	}

	@Override
	public boolean createJob(Job job) {
//		if (job != null) {
//			job.setId(nextId++);
//			jobs.add(job);
//			return true;
//		}
//		return false;

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
//			Job fetchJob = jobs.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
			Job fetchJob = jobRepo.findById(id).orElse(null);
			if (fetchJob != null) {
				fetchJob.setDescription(job.getDescription());
				fetchJob.setTitle(job.getTitle());
				fetchJob.setLocation(job.getLocation());
				fetchJob.setStatus(job.getStatus());
				fetchJob.setMinSalary(job.getMinSalary());
				fetchJob.setMaxSalary(job.getMaxSalary());

				jobRepo.save(fetchJob);
			}

			return fetchJob;
		}

	}

	@Override
	public Job deleteJob(Integer id) {
//		Job job = jobs.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
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
