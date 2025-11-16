package com.example.demo.repository;

import com.example.demo.model.Movie;
import java.util.List;

public interface MovieRepository {
    List<Movie> getAllMovies();
    List<Movie> findByDirector(String director);
    void addMovie(Movie movie);
}