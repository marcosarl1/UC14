package com.senac.service;

import com.senac.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getMovieReviews(Integer movieId);
    Review getReviewById(Integer id);
    Review addReview(Review review);
    Review updateReview(Integer id, Review review);
    void deleteReview(Integer movieId, Integer id);
}
