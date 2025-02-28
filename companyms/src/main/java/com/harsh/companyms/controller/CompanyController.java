package com.harsh.companyms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.companyms.dto.Pair;
import com.harsh.companyms.model.Company;
import com.harsh.companyms.service.CompanyService;

@RestController
public class CompanyController {

	private CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping("/companies/load")
	public ResponseEntity<Pair> loadCompanies() {
		boolean companiesLoaded = companyService.loadCompanies();
		if (companiesLoaded) {
			return new ResponseEntity<>(new Pair("succcess", "Companes loaded successfully !!"), HttpStatus.CREATED);

		}
		return new ResponseEntity<>(new Pair("fail", "Error occured while loading companies!"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/companies")
	public ResponseEntity<Pair> getAllCompanies() {

		List<Company> companies = companyService.getAllCompanies();

		if (companies.size() == 0) {
			return new ResponseEntity<Pair>(new Pair("success", "No companies found!!"), HttpStatus.OK);
		}
		return new ResponseEntity<Pair>(new Pair("success", companies), HttpStatus.OK);

	}

	@GetMapping("/companies/id/{id}")
	public ResponseEntity findJobById(@PathVariable int id) {
		Company company = companyService.getCompanyById(id);
		if (company == null) {
			return new ResponseEntity<>(new Pair("fail", "Company Id not found !!"), HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<>(company, HttpStatus.OK);
	}

	@GetMapping("/companies/find")
	public ResponseEntity<Pair> findJobById2(@RequestParam(value = "id", required = true) String id) {
		Company company = companyService.getCompanyById(Integer.parseInt(id));
		if (company == null) {
			return new ResponseEntity<>(new Pair("fail", "Company Id not found !!"), HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<>(new Pair("success", company), HttpStatus.OK);
	}

	@PostMapping("/companies")
	public ResponseEntity<Pair> createJob(@RequestBody Company company) {
		boolean companyCreated = companyService.createCompany(company);
		if (companyCreated) {
			return new ResponseEntity<Pair>(new Pair("success", "Company created Successfully!!"), HttpStatus.CREATED);
		}
		return new ResponseEntity<Pair>(new Pair("fail", "Something went wrong !"), HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/companies/{id}")
	public ResponseEntity<Pair> updateJob(@RequestBody Company company, @PathVariable int id) {
		Company updatedCompany = companyService.updateCompany(company, id);
		if (updatedCompany == null) {
			return new ResponseEntity<Pair>(new Pair("fail", "Something went wrong !"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Pair>(new Pair("success", "Company updated Successfully!!"), HttpStatus.OK);

	}

	@DeleteMapping("/companies/{id}")
	public ResponseEntity<Pair> deleteJob(@PathVariable int id) {
		Company deletedCompany = companyService.deleteCompanyById(id);
		if (deletedCompany == null) {
			return new ResponseEntity<Pair>(new Pair("fail", "Something went wrong !"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Pair>(new Pair("success", "Company deleted !!"), HttpStatus.OK);
	}

}
