package com.harsh.jobapp.service;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.*;

import org.springframework.stereotype.Service;

import com.harsh.jobapp.model.Company;
import com.harsh.jobapp.model.Review;
import com.harsh.jobapp.repositories.CompanyRepository;
import com.harsh.jobapp.repositories.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepo;

	private CompanyService companyService;

	public ReviewServiceImpl(ReviewRepository reviewRepo, CompanyService companyService) {
		this.reviewRepo = reviewRepo;
		this.companyService = companyService;
	}

	@Override
	public List<Review> getAllReviews(int companyID) {
		List<Review> reviews = reviewRepo.findByCompanyId(companyID);
		return reviews;
	}

	@Override
	public boolean addReviewForCompany(Review review, int companyId) {

		Company company = companyService.getCompanyById(companyId);
		if (company != null && review != null) {
			review.setCompany(company);
			reviewRepo.save(review);
			return true;
		}
		return false;
	}

	@Override
	public Review findReviewById(int companyId, int reviewId) {

		List<Review> allReviews = this.getAllReviews(companyId);
		if (allReviews.size() > 0) {
			Review review = allReviews.stream().filter(r -> r.getId().equals(reviewId)).findFirst().orElse(null);
			if (review != null) {
				return review;
			}
		}
		return null;
	}

	@Override
	public Review updateReview(int companyId, int reviewId, Review review) {
		Review fetchedReview = this.findReviewById(companyId, reviewId);
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
	public Review deleteReview(int companyId, int reviewId) {

		Review review = this.findReviewById(companyId, reviewId);
		if (review != null) {
			reviewRepo.delete(review);
			return review;
		}
		return null;
	}

}
