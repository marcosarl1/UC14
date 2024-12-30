package com.senac.service;

import com.senac.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviews();
    Review getReviewById(Integer id);
    Review addReview(Review movie);
    Review updateReview(Integer id, Review review);
    void deleteReview(Integer id);
}
