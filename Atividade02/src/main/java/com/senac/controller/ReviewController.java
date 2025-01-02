package com.senac.controller;

import com.senac.model.Movie;
import com.senac.model.Review;
import com.senac.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies/{movieId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping()
    public ResponseEntity<List<Review>> getAllMovieReviews(@PathVariable Integer movieId) {
        List<Review> reviews = reviewService.getMovieReviews(movieId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Integer movieId, @PathVariable Integer id) {
        Review review = reviewService.getReviewById(id);
        setReviewMovieId(review, movieId);
        return ResponseEntity.ok(review);
    }

    @PostMapping("/add")
    public ResponseEntity<Review> addReview(@PathVariable Integer movieId, @Valid @RequestBody Review review) {
        setReviewMovieId(review, movieId);
        Review savedReview = reviewService.addReview(review);
        return ResponseEntity.ok(savedReview);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Integer movieId, @PathVariable Integer id, @Valid @RequestBody Review review) {
        setReviewMovieId(review, movieId);
        Review updatedReview = reviewService.updateReview(id, review);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable Integer movieId, @PathVariable Integer id) {
        reviewService.deleteReview(movieId, id);
        return ResponseEntity.noContent().build();
    }

    private void setReviewMovieId(Review review, Integer movieId) {
        review.setMovie(new Movie(movieId));
    }
}