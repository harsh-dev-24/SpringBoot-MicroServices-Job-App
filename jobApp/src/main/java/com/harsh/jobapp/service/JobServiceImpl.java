package com.harsh.jobapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.harsh.jobapp.model.Job;
import com.harsh.jobapp.repositories.JobRepository;

@Service
public class JobServiceImpl implements JobService {

	private JobRepository jobRepo;

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
	public List<Job> findAllJobs() {
//		return jobs;
		return jobRepo.findAll();
	}

	@Override
	public Job findJobById(Integer id) {
		Job job = jobRepo.findById(id).orElse(null);
//		Job job = jobs.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
		if (job == null) {
			return null;
		} else {
			return job;
		}
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
			Job fetchJob = findJobById(id);
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
		Job job = findJobById(id);
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
