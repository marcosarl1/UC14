package com.senac.controller;

import com.senac.model.Review;
import com.senac.service.MovieService;
import com.senac.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final MovieService movieService;

    public ReviewController(ReviewService reviewService, MovieService movieService) {
        this.reviewService = reviewService;
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public String getReviews(@PathVariable Integer id, Model model) {
        List<Review> reviews = reviewService.getMovieReviewById(id);
        model.addAttribute("movie", movieService.getMovieById(id));
        model.addAttribute("review", new Review());
        model.addAttribute("reviews", reviews);
        return "reviews";
    }

    @PostMapping("/add/{movieId}")
    public String addReview(@PathVariable Integer movieId, @ModelAttribute Review review) {
        reviewService.addReview(movieId, review);
        return "redirect:/movies/details/" + movieId;
    }
}