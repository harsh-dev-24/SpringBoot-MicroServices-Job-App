package com.harsh.jobapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.jobapp.dto.Pair;
import com.harsh.jobapp.model.Job;
import com.harsh.jobapp.service.JobService;

@RestController
public class JobController {

	private final JobService jobService;

	// Constructor Injection
	@Autowired
	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Pair> loadJobs() {
		boolean jobsLoaded = jobService.loadJobs();
		if (jobsLoaded) {
			return new ResponseEntity<Pair>(new Pair("success", "Jobs Loaded Successfully!!"), HttpStatus.OK);
		}
		return new ResponseEntity<Pair>(new Pair("fail", "Problem loading Jobs...!"), HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/jobs")
	public ResponseEntity<Pair> findAllJobs() {
		List<Job> allJobs = jobService.findAllJobs();
		if (allJobs.size() == 0) {
			return new ResponseEntity<Pair>(new Pair("success", "No jobs found!!"), HttpStatus.OK);
		}
		return new ResponseEntity<Pair>(new Pair("success", allJobs), HttpStatus.OK);
	}

	@GetMapping("/jobs/id/{id}")
	public ResponseEntity<Pair> findJobById(@PathVariable int id) {
		Job job = jobService.findJobById(id);
		if (job == null) {
			return new ResponseEntity<>(new Pair("fail", "Job Id not found !!"), HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<>(new Pair("success", job), HttpStatus.OK);
	}

	@GetMapping("/jobs/find")
	public ResponseEntity<Pair> findJobById2(@RequestParam(value = "id", required = true) String id) {
		Job job = jobService.findJobById(Integer.parseInt(id));
		if (job == null) {
			return new ResponseEntity<>(new Pair("fail", "Job Id not found !!"), HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<>(new Pair("success", job), HttpStatus.OK);
	}

	@PostMapping("/jobs")
	public ResponseEntity<Pair> createJob(@RequestBody Job job) {
		boolean jobCreated = jobService.createJob(job);
		if (jobCreated) {
			return new ResponseEntity<Pair>(new Pair("success", "Job created Successfully!!"), HttpStatus.CREATED);
		}
		return new ResponseEntity<Pair>(new Pair("fail", "Something went wrong !"), HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/jobs/{id}")
	public ResponseEntity<Pair> updateJob(@RequestBody Job job, @PathVariable int id) {
		Job jobUpdated = jobService.updateJob(job, id);
		if (jobUpdated == null) {
			return new ResponseEntity<Pair>(new Pair("fail", "Something went wrong !"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Pair>(new Pair("success", "Job updated Successfully!!"), HttpStatus.OK);

	}

	@DeleteMapping("/jobs/{id}")
	public ResponseEntity<Pair> deleteJob(@PathVariable int id) {
		Job deletedJob = jobService.deleteJob(id);
		if (deletedJob == null) {
			return new ResponseEntity<Pair>(new Pair("fail", "Something went wrong !"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Pair>(new Pair("success", "Job deleted !!"), HttpStatus.OK);
	}
	/*
	 * @GetMapping("/jobs/delete") public ResponseEntity<String>
	 * deleteJobs(@RequestParam String id) { String[] deleteIds =
	 * id.trim().split(","); return new ResponseEntity<String>(id, HttpStatus.OK); }
	 */

	@GetMapping("/jobs/search")
	public ResponseEntity<Pair> jobSearch(@RequestParam(value = "key", required = true) String key) {
		List<Job> jobs = jobService.searchJob(key);
		if (jobs == null) {
			return new ResponseEntity<Pair>(new Pair("success", "No jobs found!!"), HttpStatus.OK);
		}
		return new ResponseEntity<Pair>(new Pair("success", jobs), HttpStatus.OK);
	}

	@GetMapping("/jobs/status/{status}")
	public ResponseEntity<Pair> findJobsByStatus(@PathVariable String status) {

		List<Job> jobs = jobService.findJobsByStatus(status);
		if (jobs == null) {
			return new ResponseEntity<Pair>(new Pair("success", "No jobs found!!"), HttpStatus.OK);
		}
		return new ResponseEntity<Pair>(new Pair("success", jobs), HttpStatus.OK);

	}
}
