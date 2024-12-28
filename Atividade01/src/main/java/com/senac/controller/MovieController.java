package com.senac.controller;

import com.senac.model.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private List<Movie> movies = new ArrayList<>();

    @GetMapping
    public String listAllMovies(Model model) {
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/add")
    public String formNewMovie(Model model) {
        model.addAttribute("movie", new Movie());
        return "new-movie";
    }

    @PostMapping("/add")
    public String addMovie(@ModelAttribute Movie movie) {
        Integer id = movies.size() + 1;
        movie.setId(id);
        movies.add(movie);
        return "redirect:/movies";
    }

    @GetMapping("/details/{id}")
    public String movieDetails(@PathVariable Integer id, Model model) {
        Movie movie = movies.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (movie == null) {
            model.addAttribute("error", "Filme não encontrado");
        }
        model.addAttribute("movie", movie);
        return "movie-details";
    }
}