package com.senac.service.impl;

import com.senac.exception.MovieNotFoundException;
import com.senac.exception.ReviewNotFoundException;
import com.senac.model.Review;
import com.senac.repository.MovieRepository;
import com.senac.repository.ReviewRepository;
import com.senac.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final String reviewNotFoundMsg = "Análise não encontrada com ID: ";

    public ReviewServiceImpl(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Review> getMovieReviews(Integer movieId) {
        return reviewRepository.findByMovieId(movieId);
    }

    @Override
    public Review getReviewById(Integer id) {
        validateId(id);
        return reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(reviewNotFoundMsg + id));
    }

    @Override
    public Review addReview(Review review) {
        validateMovieExists(review.getMovie().getId());
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Integer id, Review review) {
        validateId(id);
        validateMovieExists(review.getMovie().getId());
        return reviewRepository.findById(id)
                .map(existingReview -> {
                    review.setId(id);
                    return reviewRepository.save(review);
                }).orElseThrow(() -> new ReviewNotFoundException(reviewNotFoundMsg + id));
    }

    @Override
    public void deleteReview(Integer movieId, Integer id) {
        validateId(id);
        validateMovieExists(movieId);
        if (!reviewRepository.existsById(id)) {
            throw new ReviewNotFoundException(reviewNotFoundMsg + id);
        }
        reviewRepository.deleteById(id);
    }

    private void validateId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id do filme deve ser um número positivo");
        }
    }

    private void validateMovieExists(Integer movieId) {
        if (movieId == null || !movieRepository.existsById(movieId)) {
            throw new MovieNotFoundException("Filme não encontrado com ID: " + movieId);
        }
    }
}
