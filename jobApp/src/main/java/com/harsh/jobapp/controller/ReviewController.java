package com.harsh.jobapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.jobapp.dto.Pair;
import com.harsh.jobapp.model.Job;
import com.harsh.jobapp.model.Review;
import com.harsh.jobapp.service.ReviewService;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@GetMapping("/reviews")
	public ResponseEntity<Pair> findAllReviews(@PathVariable int companyId) {
		List<Review> allReviews = reviewService.getAllReviews(companyId);
		if (allReviews.size() == 0) {
			return new ResponseEntity<Pair>(new Pair("success", "No reviews found!!"), HttpStatus.OK);
		}
		return new ResponseEntity<Pair>(new Pair("success", allReviews), HttpStatus.OK);
	}
	
	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<Pair> findReviewById(@PathVariable int companyId, @PathVariable int reviewId) {
		Review review = reviewService.findReviewById(companyId, reviewId);
		if (review == null) {
			return new ResponseEntity<Pair>(new Pair("success", "No review found!!"), HttpStatus.OK);
		}
		return new ResponseEntity<Pair>(new Pair("success", review), HttpStatus.OK);
	}

	@PostMapping("/reviews")
	public ResponseEntity<Pair> addReview(@RequestBody Review review, @PathVariable int companyId) {
		boolean reviewCompany = reviewService.addReviewForCompany(review, companyId);
		if (reviewCompany) {
			return new ResponseEntity<Pair>(new Pair("success", "Review added Successfully!!"), HttpStatus.CREATED);
		}
		return new ResponseEntity<Pair>(new Pair("fail", "Something went wrong !"), HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<Pair> updateJob(@RequestBody Review review, @PathVariable int companyId,@PathVariable int reviewId ) {
		Review reviewUpdated = reviewService.updateReview(companyId, reviewId, review);
		if (reviewUpdated == null) {
			return new ResponseEntity<Pair>(new Pair("fail", "Something went wrong !"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Pair>(new Pair("success", "Review updated Successfully!!"), HttpStatus.OK);

	}
	

	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<Pair> deleteJob(@PathVariable int companyId,@PathVariable int reviewId) {
		Review deletedReview = reviewService.deleteReview(companyId, reviewId);
		if (deletedReview == null) {
			return new ResponseEntity<Pair>(new Pair("fail", "Something went wrong !"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Pair>(new Pair("success", "Review deleted !!"), HttpStatus.OK);
	}

}
