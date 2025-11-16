package com.example.demo.repository;

import com.example.demo.model.Movie;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private static final List<Movie> MOVIES = new ArrayList<>();

    static {
        MOVIES.add(new Movie("Inception", "Christopher Nolan", 2010));
        MOVIES.add(new Movie("The Matrix", "Lana Wachowski, Lilly Wachowski", 1999));
        MOVIES.add(new Movie("Interstellar", "Christopher Nolan", 2014));
        MOVIES.add(new Movie("Властелин колец: Две крепости", "Питер Джексон", 2002));
        MOVIES.add(new Movie("Назад в будущее", "Роберт Земекис", 1985));
    }

    @Override
    public List<Movie> getAllMovies() {
        return new ArrayList<>(MOVIES);
    }

    @Override
    public List<Movie> findByDirector(String director) {
        return MOVIES.stream()
                .filter(m -> m.getDirector().equalsIgnoreCase(director))
                .toList();
    }

    @Override
    public void addMovie(Movie movie) {
        MOVIES.add(movie);
    }
}
