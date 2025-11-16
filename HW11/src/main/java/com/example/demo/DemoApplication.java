package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}


//http://localhost:8080/api/movies/all весь список фильмов
//http://localhost:8080/api/movies/by-director?name=Christopher+Nolan фильтр по режиссеру Nolan