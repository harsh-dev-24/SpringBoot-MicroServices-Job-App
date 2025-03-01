package com.harsh.jobms.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.harsh.jobms.external.Company;

@FeignClient(name = "COMPANY-SERVICE") // declares this interface as feign client for COMPANY-SERVICE
public interface CompanyClient {

	@GetMapping("/companies/id/{companyId}")
	Company getCompany(@PathVariable Integer companyId);

}
