package com.myorg.functionalProgramming.declarative.functionalInterfaces.preDefined.predicate;

import java.util.function.BiPredicate;

public class BiPredicateDemo {
    public static void main(String[] args) {
        // BiPredicate receives two parameters of defined types and always return a boolean value
        BiPredicate<Integer, String> biPredicate = (i, s) -> s.length() >= i;
        System.out.println(biPredicate.test(5, "Hello"));
        System.out.println(biPredicate.test(5, "Hi"));
    }
}
