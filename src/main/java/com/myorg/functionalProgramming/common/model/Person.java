package com.myorg.functionalProgramming.common.model;

import com.myorg.functionalProgramming.common.statics.Gender;

public class Person {
    private final String name;
    private final Gender gender;

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}
