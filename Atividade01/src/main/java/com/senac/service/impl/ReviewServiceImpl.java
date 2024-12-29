package com.senac.service.impl;

import com.senac.model.Movie;
import com.senac.model.Review;
import com.senac.service.MovieService;
import com.senac.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final List<Review> reviews = new ArrayList<>();
    private final MovieService movieService;

    public ReviewServiceImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public List<Review> getMovieReviewById(Integer movieId) {
        return reviews.stream()
                .filter(a -> a.getMovie().getId().equals(movieId))
                .toList();
    }

    @Override
    public Review addReview(Integer movieId, Review review) {
        Movie movie = movieService.getMovieById(movieId);
        if (movie != null) {
            review.setId(reviews.size() + 1);
            review.setMovie(movie);
            reviews.add(review);
        }
        return review;
    }
}
