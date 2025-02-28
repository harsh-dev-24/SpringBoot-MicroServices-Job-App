package com.harsh.reviewms.service;

import java.util.List;

import com.harsh.reviewms.model.Review;

public interface ReviewService {

	List<Review> getAllReviews(Integer companyID);

	boolean addReviewForCompany(Review review, Integer companyId);

	Review findReviewById(int reviewId);

	Review updateReview(int reviewId, Review review);

	Review deleteReview(int reviewId);

}
