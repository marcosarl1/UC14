package com.senac.service;

import com.senac.model.Movie;

import java.util.List;

public interface ReviewService {
    List<Movie> getReviews();
    Movie getReviewById(Integer id);
    Movie addReview(Movie movie);
    Movie updateReview(Integer id, Movie movie);
    void deleteReview(Integer id);
}
