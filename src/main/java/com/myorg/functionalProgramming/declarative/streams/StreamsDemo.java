package com.myorg.functionalProgramming.declarative.streams;

import com.myorg.functionalProgramming.common.model.Person;

import java.util.List;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static com.myorg.functionalProgramming.common.statics.Gender.*;

public class StreamsDemo {
    public static void main(String[] args) {

        List<Person> people = List.of(
                new Person("John", MALE),
                new Person("Stephanie", FEMALE),
                new Person("Roman", MALE),
                new Person("Dwayne", MALE),
                new Person("Alexa", FEMALE),
                new Person("Naya", FEMALE),
                new Person("Seth", MALE),
                new Person("Ronda", FEMALE),
                new Person("Bob", PREFER_NOT_TO_SAY)
        );

        // The big picture on what's really happening inside streams ---------------------------------------------------

        Function<Person, String> personStringFunction = person -> person.getName();
        ToIntFunction<String> toIntFunctionGetLength = String::length; // s -> s.length();
        IntConsumer intConsumerPrint = System.out::println; // i -> System.out.println(i);

        people.stream()
                .map(personStringFunction)
                .mapToInt(toIntFunctionGetLength)
                .forEach(intConsumerPrint);


        // Simple example : Obtaining each gender into a set and print each value in the set separately ----------------
        people.stream()
                .map(person -> person.getGender())
                .collect(Collectors.toSet())
                .forEach(System.out::println);


        // Will return true if all the records satisfy the predicate. Here returns true if all persons are female
        boolean containsFemalesOnly = people.stream().allMatch(person -> FEMALE.equals(person.getGender()));
        System.out.println("containsFemalesOnly : " + containsFemalesOnly); //false

        // Returns true if any record matches with the predicate. In this case returns true if there is any female
        boolean anyMatchFemale = people.stream().anyMatch(person -> FEMALE.equals(person.getGender()));
        System.out.println("anyMatchFemale : " + anyMatchFemale);// true

        Predicate<Person> malePersonPredicate = p -> MALE.equals(p.getGender());
        boolean anyMatchMale = people.stream().anyMatch(malePersonPredicate);// true
        System.out.println("anyMatchMale : " + anyMatchMale);

        /** Returns true if no item/object matches with the provided predicate. Here it returns true if no person
         * available in the list with Gender - OTHER*/
        boolean nonMatchOtherGender = people.stream().noneMatch(p -> OTHER.equals(p.getGender()));
        System.out.println("nonMatchOtherGender : " + nonMatchOtherGender);
    }
}
