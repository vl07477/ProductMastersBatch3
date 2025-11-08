package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Инициализируем Spring-контекст
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Внедряем конкретный бин по имени
        GreetingService greetingService = (GreetingService) context.getBean("russianGreetingService");

        greetingService.sayHello(); // Выведет: Привет!
    }
}