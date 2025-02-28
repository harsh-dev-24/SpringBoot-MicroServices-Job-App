package com.harsh.companyms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.harsh.companyms.model.Company;
import com.harsh.companyms.repository.CompanyRepository;
import com.harsh.companyms.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	private CompanyRepository companyRepo;

	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepo = companyRepository;
	}

	private List<Company> listOfCompanies = new ArrayList<Company>(
			List.of(new Company("TCS", "Tata Consultancy Services is a major IT giant in India"),
					new Company("Wipro", "Wipro is an AI leader IT Company in India")));

	@Override
	public List<Company> getAllCompanies() {
		return companyRepo.findAll();
	}

	@Override
	public boolean createCompany(Company company) {
		if (company != null) {
			companyRepo.save(company);
			return true;
		}
		return false;
	}

	@Override
	public Company updateCompany(Company company, int id) {
		if (company == null) {
			return null;
		} else {
			Company fetchCompany = getCompanyById(id);
			if (fetchCompany != null) {
				fetchCompany.setName(company.getName());
				fetchCompany.setDescription(company.getDescription());
				companyRepo.save(fetchCompany);
			}

			return fetchCompany;
		}
	}

	@Override
	public Company getCompanyById(int id) {
		Company company = companyRepo.findById(id).orElse(null);
		if (company == null) {
			return null;
		} else {
			return company;
		}
	}

	@Override
	public Company deleteCompanyById(int id) {
		Company company = getCompanyById(id);
		if (company != null) {
			companyRepo.delete(company);
		}
		return company;
	}

	@Override
	public boolean loadCompanies() {
		if (listOfCompanies.size() > 0) {
			companyRepo.saveAll(listOfCompanies);
			return true;
		}
		return false;
	}

}
