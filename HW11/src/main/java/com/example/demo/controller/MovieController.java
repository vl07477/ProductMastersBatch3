package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/all", produces = "application/json; charset=UTF-8")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping(value = "/by-director", produces = "application/json; charset=UTF-8")
    public List<Movie> getByDirector(@RequestParam("name") String name) {
        return movieService.findByDirector(name);
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        if (movie.getTitle() == null || movie.getTitle().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Поле title не должно быть пустым");
        }
        if (movie.getDirector() == null || movie.getDirector().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Поле director не должно быть пустым");
        }
        if (movie.getYear() < 1900) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Год фильма должен быть >= 1900");
        }
        movieService.addMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body("Фильм добавлен");
    }
}