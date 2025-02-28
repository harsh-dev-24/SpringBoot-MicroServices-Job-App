package com.harsh.reviewms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.harsh.reviewms.model.Review;
import com.harsh.reviewms.repository.ReviewRepository;
import com.harsh.reviewms.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepo;

	public ReviewServiceImpl(ReviewRepository reviewRepo) {
		this.reviewRepo = reviewRepo;
	}

	@Override
	public List<Review> getAllReviews(Integer companyID) {
		List<Review> reviews = reviewRepo.findByCompanyId(companyID);
		return reviews;
	}

	@Override
	public boolean addReviewForCompany(Review review, Integer companyId) {

//		Company company = companyService.getCompanyById(companyId);

		if (companyId != null && review != null) {
			review.setCompanyId(companyId);
//			review.setCompany(company);
			reviewRepo.save(review);
			return true;
		}
		return false;
	}

	@Override
	public Review findReviewById(int reviewId) {

//		List<Review> allReviews = this.getAllReviews(companyId);
//		if (allReviews.size() > 0) {
//			Review review = allReviews.stream().filter(r -> r.getId().equals(reviewId)).findFirst().orElse(null);
//			if (review != null) {
//				return review;
//			}
//		}

		Review review = reviewRepo.findById(reviewId).orElse(null);
		if (review != null) {
			return review;
		}
		return null;
	}

	@Override
	public Review updateReview(int reviewId, Review review) {
//		Review fetchedReview = this.findReviewById(companyId, reviewId);
		Review fetchedReview = reviewRepo.findById(reviewId).orElse(null);

		if (fetchedReview != null & review != null) {
			fetchedReview.setTitle(review.getTitle());
			fetchedReview.setDescription(review.getDescription());
			fetchedReview.setRating(review.getRating());
			reviewRepo.save(fetchedReview);
			return fetchedReview;
		}
		return null;
	}

	@Override
	public Review deleteReview(int reviewId) {

		Review review = this.findReviewById(reviewId);
		if (review != null) {
			reviewRepo.delete(review);
			return review;
		}
		return null;
	}

}
