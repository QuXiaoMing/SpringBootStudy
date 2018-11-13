package com.example.lesson3;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class ServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Lesson3Application.class);
    }

}
