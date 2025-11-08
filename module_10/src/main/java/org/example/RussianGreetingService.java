package org.example;

import org.springframework.stereotype.Component;

@Component("russianGreetingService")
public class RussianGreetingService implements GreetingService {
    @Override
    public void sayHello() {
        System.out.println("Привет!");
    }
}