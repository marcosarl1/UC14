package com.senac.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Movie {

    private Integer id;

    @NotBlank(message = "Por favor, informe um título válido.")
    private String title;

    @NotBlank(message = "Por favor, forneça uma sinopse para o filme.")
    private String synopsis;

    @NotBlank(message = "Por favor, informe pelo menos um gênero para o filme.")
    private String genre;

    @NotNull(message = "Por favor, informe um ano de lançamento válido.")
    @Min(value = 1800, message = "O ano de lançamento deve ser após 1800")
    @Max(value = 2100, message = "O ano de lançamento deve ser antes de 2100")
    private Integer releaseYear;

    public Movie() {
    }

    public Movie(Integer id, String title, String synopsis, String genre, Integer releaseYear) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
}
