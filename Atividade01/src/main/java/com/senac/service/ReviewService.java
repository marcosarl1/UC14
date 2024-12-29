package com.senac.service;

import com.senac.model.Movie;
import com.senac.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getMovieReviewById(Integer movieId);
    Review addReview(Integer movieId, Review review);
}
