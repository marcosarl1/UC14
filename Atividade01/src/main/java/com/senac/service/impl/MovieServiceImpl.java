package com.senac.service.impl;

import com.senac.model.Movie;
import com.senac.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    List<Movie> movies = new ArrayList<>();

    @Override
    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public Movie getMovieById(Integer id) {
        return movies.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Movie addMovie(Movie movie) {
        movie.setId(movies.size() + 1);
        movies.add(movie);
        return movie;
    }
}
