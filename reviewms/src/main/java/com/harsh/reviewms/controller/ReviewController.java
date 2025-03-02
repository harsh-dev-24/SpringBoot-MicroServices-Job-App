package com.harsh.reviewms.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.reviewms.dto.Pair;
import com.harsh.reviewms.messaging.ReviewMessageProducer;
import com.harsh.reviewms.model.Review;
import com.harsh.reviewms.service.ReviewService;

@RestController
//@RequestMapping("/companies/{companyId}")
@RequestMapping("/reviews")
public class ReviewController {

	private ReviewService reviewService;

	private ReviewMessageProducer reviewMessageProducer;

	public ReviewController(ReviewService reviewService, ReviewMessageProducer reviewMessageProducer) {
		this.reviewService = reviewService;
		this.reviewMessageProducer = reviewMessageProducer;
	}

	@GetMapping
	public ResponseEntity findAllReviews(@RequestParam Integer companyId) {
		List<Review> allReviews = reviewService.getAllReviews(companyId);
		return new ResponseEntity<>(allReviews, HttpStatus.OK);

	}

	@GetMapping("/{reviewId}")
	public ResponseEntity<Pair> findReviewById(@PathVariable int reviewId) {
		Review review = reviewService.findReviewById(reviewId);
		if (review == null) {
			return new ResponseEntity<Pair>(new Pair("success", "No review found!!"), HttpStatus.OK);
		}
		return new ResponseEntity<Pair>(new Pair("success", review), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Pair> addReview(@RequestBody Review review, @RequestParam int companyId) {
		boolean reviewCompany = reviewService.addReviewForCompany(review, companyId);
		if (reviewCompany) {
			reviewMessageProducer.sendMessage(review);
			return new ResponseEntity<Pair>(new Pair("success", "Review added Successfully!!"), HttpStatus.CREATED);
		}
		return new ResponseEntity<Pair>(new Pair("fail", "Something went wrong !"), HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/{reviewId}")
	public ResponseEntity<Pair> updateJob(@RequestBody Review review, @PathVariable int reviewId) {
		Review reviewUpdated = reviewService.updateReview(reviewId, review);
		if (reviewUpdated == null) {
			return new ResponseEntity<Pair>(new Pair("fail", "Something went wrong !"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Pair>(new Pair("success", "Review updated Successfully!!"), HttpStatus.OK);

	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<Pair> deleteJob(@PathVariable int reviewId) {
		Review deletedReview = reviewService.deleteReview(reviewId);
		if (deletedReview == null) {
			return new ResponseEntity<Pair>(new Pair("fail", "Something went wrong !"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Pair>(new Pair("success", "Review deleted !!"), HttpStatus.OK);
	}

	@GetMapping("/averageRating")
	public Double getAverageRating(@RequestParam Integer companyId) {
		Double avgRating = reviewService.getAverageRating(companyId);
		return avgRating;
	}

}
