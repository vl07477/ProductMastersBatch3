package com.example.demo.repository;

import com.example.demo.model.Movie;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository  // Важно! чтобы Spring видел бин
public class MovieRepositoryImpl implements MovieRepository {

    private final List<Movie> movies = List.of(
            new Movie("Pobeg iz Shoushenga", "Frank Ducke", 1994),
            new Movie("Властелин колец: Две крепости", "Питер Джексон", 2002),
            new Movie("Назад в будущее", "Роберт Земекис", 1985),
            new Movie("Назад в будущее 2", "Роберт Земекис", 1989),
            new Movie("Гараж", "Эльдар Рязанов", 1979),
            new Movie("Три плюс два", "Генрих Оганисян", 1963),
            new Movie("фильм 1", "неизвестный", 2025),
            new Movie("фильм 2", "неизвестный", 2026),
            new Movie("фильм 2", "неизвестный", 2027)
            // можно добавить остальные
    );

    @Override
    public List<Movie> findAll() {
        return movies;
    }
}
