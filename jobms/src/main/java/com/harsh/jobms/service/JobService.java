package com.harsh.jobms.service;

import java.util.List;

import com.harsh.jobms.dto.JobCompanyDTO;
import com.harsh.jobms.model.Job;

public interface JobService {

	List<JobCompanyDTO> findAllJobs();

	Job findJobById(Integer id);

	boolean createJob(Job job);

	Job updateJob(Job job, Integer id);

	Job deleteJob(Integer id);

	boolean loadJobs();

	List<Job> searchJob(String keyword);

	List<Job> findJobsByStatus(String status);

}
