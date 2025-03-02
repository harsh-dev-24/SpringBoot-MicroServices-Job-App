package com.harsh.companyms.service;

import java.util.List;

import com.harsh.companyms.dto.ReviewMessage;
import com.harsh.companyms.model.Company;

public interface CompanyService {

	List<Company> getAllCompanies();

	boolean createCompany(Company company);

	Company updateCompany(Company company, int id);

	Company getCompanyById(int id);

	Company deleteCompanyById(int id);

	boolean loadCompanies();

	void updateCompanyRating(ReviewMessage reviewMessage);

}
