package com.harsh.jobapp.service;

import java.util.List;

import com.harsh.jobapp.model.Company;

public interface CompanyService {

	List<Company> getAllCompanies();

	boolean createCompany(Company company);

	Company updateCompany(Company company, int id);

	Company getCompanyById(int id);

	Company deleteCompanyById(int id);

	boolean loadCompanies();

}
