package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}


//http://localhost:8080/api/movies/all весь список фильмов easy
//http://localhost:8080/api/movies/by-director?name=Christopher+Nolan фильтр по режиссеру Nolan medium

/*
curl -X POST http://localhost:8080/api/movies/add \
  -H "Content-Type: application/json" \
  -d '{"title":"film1","director":"Unknown","year":2026}'

  курл для добавления фильма метод Post - hard

 */