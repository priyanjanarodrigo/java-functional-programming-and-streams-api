package com.myorg.functionalProgramming.declarative.functionalInterfaces.custom;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@FunctionalInterface
interface MyFunctionalInterface {

    default int getTotalOf(int... values) {
        int total = 0;
        if (Objects.isNull(values) || values.length < 1) {
            return total;
        }
        return Arrays.stream(values).reduce((n1, n2) -> n1 + n2).getAsInt();
    }

    default Integer multiplyByValue(int value, int multiplyByValue) {
        return value * multiplyByValue;
    }

    static String convertToLowercase(String text) {
        return Optional.ofNullable(text).map(String::toLowerCase).orElseGet(null);
    }

    static void display(String text) {
        String displayText = Optional.ofNullable(text)
                .map(String::toLowerCase)
                .orElseGet(() -> "NO_TEXT_AVAILABLE");
        System.out.println(displayText);
    }

    // Single abstract method
    String convertToUpperCase(String text);
}
