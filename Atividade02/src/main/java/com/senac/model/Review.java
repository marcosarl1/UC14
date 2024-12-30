package com.senac.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @NotBlank(message = "Por favor, insira sua análise.")
    private String review;

    @NotNull(message = "Por favor, forneça sua nota para o filme.")
    @Min(value = 0, message = "A nota mínima permitida é 0.")
    @Max(value = 5, message = "A nota máxima permitida é 5. ")
    private Double score;

    public Review() {
    }

    public Review(Integer id, Movie movie, String review, Double score) {
        this.id = id;
        this.movie = movie;
        this.review = review;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
