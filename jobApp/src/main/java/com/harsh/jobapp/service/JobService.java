package com.harsh.jobapp.service;

import java.util.List;

import com.harsh.jobapp.model.Job;

public interface JobService {

	List<Job> findAllJobs();

	Job findJobById(Integer id);

	boolean createJob(Job job);

	Job updateJob(Job job, Integer id);

	Job deleteJob(Integer id);

	boolean loadJobs();

	List<Job> searchJob(String keyword);

	List<Job> findJobsByStatus(String status);

}
