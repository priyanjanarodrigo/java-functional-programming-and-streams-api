package com.myorg.functionalProgramming.declarative.functionalInterfaces.preDefined.predicate;

import java.util.Objects;
import java.util.function.*;

public class PredicateDemo {

    // Imperative approach
    static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.startsWith("07") && phoneNumber.length() == 10;
    }

    /**
     * Predicate accepts a parameter of some data type and always return a boolean value. Below is the declarative
     * implementation of above phone number validation function
     */
    static Predicate<String> predicate = phoneNumber -> phoneNumber.startsWith("07") && phoneNumber.length() == 10;

    public static void main(String[] args) {
        System.out.println(isValidPhoneNumber("0775166779"));
        System.out.println(predicate.test("0775166779"));
        System.out.println(predicate.test("076779"));

        // Predicate chaining ------------------------------------------------------------------------------------------

        Predicate<String> nullCheckPredicate = s -> Objects.nonNull(s);
        Predicate<String> lengthPredicate = s -> s.length() > 5;

        // If we chain using and(), then all the conditions are needed to be true in order to return true (AND gate)
        boolean predicateChainOutput_AND = nullCheckPredicate
                .and(lengthPredicate)
                .test("Hi how are you");
        System.out.println("is valid (predicateChainOutput_AND) : " + predicateChainOutput_AND);

        // If we use or, if one condition returns true, then output will be true (OR gate)
        boolean predicateChainOutput_OR = nullCheckPredicate
                .or(lengthPredicate)
                .test("Hi");

        System.out.println("is valid (predicateChainOutput_OR) : " + predicateChainOutput_OR);

        // Negate returns the opposite of original output. if original output is true, then false will be returned
        boolean predicateNegate = nullCheckPredicate
                .negate().test(null);
        System.out.println("predicateNegate : " + predicateNegate);

        boolean predicateChainOutputNegate = nullCheckPredicate
                .and(lengthPredicate)
                .negate() // makes return value false
                .negate()// makes return value true again
                .test("Hi how are you");
        System.out.println("is valid (predicateChainOutputNegate) : " + predicateChainOutputNegate);

        // pre-defined variations of Predicates ------------------------------------------------------------------------

        IntPredicate intPredicate = i -> i < 100;
        System.out.println(intPredicate.test(102));

        DoublePredicate doublePredicate = d -> (d * 1.3d) < 23;
        System.out.println(doublePredicate.test(22d));

        LongPredicate longPredicate = l -> (l * 100) < 1000;
        System.out.println(longPredicate.test(50));
    }
}
