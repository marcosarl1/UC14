package com.senac.controller;

import com.senac.model.Movie;
import com.senac.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String listAllMovies(Model model) {
        model.addAttribute("movies", movieService.getMovies());
        return "movies";
    }

    @GetMapping("/add")
    public String formNewMovie(Model model) {
        model.addAttribute("movie", new Movie());
        return "add-movie";
    }

    @PostMapping("/add")
    public String addMovie(@Valid @ModelAttribute Movie movie, BindingResult result) {
        if (result.hasErrors()) {
            return "add-movie";
        }
        movieService.addMovie(movie);
        return "redirect:/movies";
    }
}