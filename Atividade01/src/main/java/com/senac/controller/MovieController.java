package com.senac.controller;

import com.senac.model.Movie;
import com.senac.model.Review;
import com.senac.service.MovieService;
import com.senac.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final ReviewService reviewService;

    public MovieController(MovieService movieService, ReviewService reviewService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String listAllMovies(Model model) {
        model.addAttribute("movies", movieService.getMovies());
        return "movies";
    }

    @GetMapping("/add")
    public String formNewMovie(Model model) {
        model.addAttribute("movie", new Movie());
        return "new-movie";
    }

    @PostMapping("/add")
    public String addMovie(@ModelAttribute Movie movie) {
        movieService.addMovie(movie);
        return "redirect:/movies";
    }

    @GetMapping("/details/{id}")
    public String movieDetails(@PathVariable Integer id, Model model) {
        List<Review> reviews = reviewService.getMovieReviewById(id);
        model.addAttribute("movie", movieService.getMovieById(id));
        model.addAttribute("review", new Review());
        model.addAttribute("reviews", reviews);
        return "movie-details";
    }
}