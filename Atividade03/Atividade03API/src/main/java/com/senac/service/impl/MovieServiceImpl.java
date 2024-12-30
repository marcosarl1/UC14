package com.senac.service.impl;

import com.senac.exception.MovieNotFoundException;
import com.senac.model.Movie;
import com.senac.repository.MovieRepository;
import com.senac.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final String movieNotFoundMsg = "Filme não encontrado com ID ";

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Integer id) {
        validateId(id);
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(movieNotFoundMsg + id));
    }

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Integer id, Movie movie) {
        validateId(id);
        return movieRepository.findById(id)
                .map(existingMovie -> {
                    movie.setId(id);
                    return movieRepository.save(movie);
                }).orElseThrow(() -> new MovieNotFoundException(movieNotFoundMsg + id));
    }

    @Override
    public void deleteMovie(Integer id) {
        validateId(id);
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException(movieNotFoundMsg + id);
        }
        movieRepository.deleteById(id);
    }

    private void validateId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id do filme deve ser um número positivo");
        }
    }

}
