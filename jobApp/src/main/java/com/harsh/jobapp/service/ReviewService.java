package com.harsh.jobapp.service;

import java.util.List;

import com.harsh.jobapp.model.Review;

public interface ReviewService {
	
	List<Review> getAllReviews(int companyID);
	
	boolean addReviewForCompany(Review review, int companyId);

	Review findReviewById(int companyId, int reviewId);

	Review updateReview(int companyId, int reviewId, Review review);

	Review deleteReview(int companyId, int reviewId);

}
