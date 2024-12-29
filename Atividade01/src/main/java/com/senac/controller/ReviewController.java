package com.senac.controller;

import com.senac.model.Review;
import com.senac.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add/{movieId}")
    public String addReview(@PathVariable Integer movieId, @ModelAttribute Review review) {
        reviewService.addReview(movieId, review);
        return "redirect:/movies/details/" + movieId;
    }
}