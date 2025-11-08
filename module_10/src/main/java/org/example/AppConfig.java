package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public GreetingService englishGreetingService() {
        return new EnglishGreetingService();
    }

    @Bean
    public GreetingService russianGreetingService() {
        return new RussianGreetingService();
    }
}