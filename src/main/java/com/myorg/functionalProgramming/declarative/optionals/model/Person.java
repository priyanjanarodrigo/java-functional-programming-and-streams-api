package com.myorg.functionalProgramming.declarative.optionals.model;

import java.util.Optional;

public class Person {
    private final String name;
    private final String email;

    public String getName() {
        return this.name;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(this.email);
    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
