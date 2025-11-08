package org.example;

import org.springframework.stereotype.Component;

@Component("englishGreetingService")
public class EnglishGreetingService implements GreetingService {
    @Override
    public void sayHello() {
        System.out.println("Hello!");
    }
}