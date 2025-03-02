package com.harsh.companyms.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.harsh.companyms.dto.ReviewMessage;
import com.harsh.companyms.service.CompanyService;

@Service
public class ReviewMessageConsumer {

	private final CompanyService companyService;

	public ReviewMessageConsumer(CompanyService companyService) {
		this.companyService = companyService;
	}

	@RabbitListener(queues = "companyRatingQueue")
	public void consumeMessage(ReviewMessage reviewMessage) {
		companyService.updateCompanyRating(reviewMessage);
	}

}
