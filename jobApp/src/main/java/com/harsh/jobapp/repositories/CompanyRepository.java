package com.harsh.jobapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harsh.jobapp.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
