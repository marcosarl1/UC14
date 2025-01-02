package com.senac.service;

import com.senac.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getMovies();
    Movie getMovieById(Integer id);
    Movie addMovie(Movie movie);
    Movie updateMovie(Integer id, Movie movie);
    void deleteMovie(Integer id);
}
