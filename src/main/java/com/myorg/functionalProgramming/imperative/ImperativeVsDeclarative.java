package com.myorg.functionalProgramming.imperative;

import com.myorg.functionalProgramming.common.model.Person;
import com.myorg.functionalProgramming.common.statics.Gender;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.myorg.functionalProgramming.common.statics.Gender.*;

public class ImperativeVsDeclarative {
    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("John", Gender.MALE),
                new Person("Stephanie", FEMALE),
                new Person("Roman", MALE),
                new Person("Dwayne", MALE),
                new Person("Alexa", FEMALE),
                new Person("Naya", FEMALE),
                new Person("Seth", MALE),
                new Person("Ronda", FEMALE)
        );

        // Imperative approach ----------------------------------------------
        List<Person> females = new ArrayList<>();

        for (Person person : people) {
            if (FEMALE.equals(person.getGender())) {
                females.add(person);
            }
        }

        for (Person female : females) {
            System.out.println(female);
        }

        // Declarative approach ---------------------------------------------
        System.out.println("\nDeclarative Approach");

        /**
         * Here we're using Streams with Collections
         * Streams allows us to go into an abstract mode where we simply define what we want (to map, filter. etc.).
         * The whole process defined with imperative approach can be implemented simply with declarative approach
         */

        // Explicitly defining a predicate
        final Predicate<Person> PERSON_PREDICATE = person -> FEMALE.equals(person.getGender());

        // Approach 1 - Just printing out the filtered results
        people.stream()
                .filter(PERSON_PREDICATE)
                .forEach(System.out::println);

        // Approach 2 - Collecting the filtered results to a list
        List<Person> femalesList = people.stream()
                .filter(person -> FEMALE.equals(person.getGender()))
                .collect(Collectors.toList());

        System.out.println(femalesList);
    }
}
