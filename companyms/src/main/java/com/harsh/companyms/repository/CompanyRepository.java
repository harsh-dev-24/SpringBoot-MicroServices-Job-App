package com.harsh.companyms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harsh.companyms.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
