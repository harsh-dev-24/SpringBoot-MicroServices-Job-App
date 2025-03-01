package com.harsh.jobms.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harsh.jobms.external.Review;

@FeignClient(name = "REVIEW-SERVICE") // declares this interface as feign client for COMPANY-SERVICE
public interface ReviewClient {

	@GetMapping("/reviews")
	List<Review> getReviews(@RequestParam Integer companyId);

}
