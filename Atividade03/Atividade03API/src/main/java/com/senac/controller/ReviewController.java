package com.senac.controller;

import com.senac.model.Review;
import com.senac.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping()
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Integer id) {
        Review review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }

    @PostMapping("/add")
    public ResponseEntity<Review> addReview(@Valid @RequestBody Review review) {
        Review savedReview = reviewService.addReview(review);
        return ResponseEntity.ok(savedReview);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Integer id, @Valid @RequestBody Review review) {
        Review updatedReview = reviewService.updateReview(id, review);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}