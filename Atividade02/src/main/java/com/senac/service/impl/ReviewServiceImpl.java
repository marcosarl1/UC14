package com.senac.service.impl;

import com.senac.exception.ReviewNotFoundException;
import com.senac.model.Review;
import com.senac.repository.ReviewRepository;
import com.senac.service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final String reviewNotFoundMsg = "Análise não encontrada com ID: ";

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReviewById(Integer id) {
        validateId(id);
        return reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(reviewNotFoundMsg + id));
    }

    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Integer id, Review review) {
        validateId(id);
        return reviewRepository.findById(id)
                .map(existingReview -> {
                    review.setId(id);
                    return reviewRepository.save(review);
                }).orElseThrow(() -> new ReviewNotFoundException(reviewNotFoundMsg + id));
    }

    @Override
    public void deleteReview(Integer id) {
        validateId(id);
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
}
