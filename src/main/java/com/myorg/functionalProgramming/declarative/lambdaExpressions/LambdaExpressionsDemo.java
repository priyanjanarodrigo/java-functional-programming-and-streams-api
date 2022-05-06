package com.myorg.functionalProgramming.declarative.lambdaExpressions;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class LambdaExpressionsDemo {

    public static void declarationAndPossibleSimplifications() {

        /** Full lambda expression declaration */
        Function<String, String> uppercaseName = (name) -> {
            return name.toUpperCase();
        };

        /** Removing braces around name parameter value (only if the number of parameters is 1) */
        Function<String, String> upperCaseName1 = name -> {
            return name.toUpperCase();
        };

        /** Removing curly braces around method body, eliminating return statement and arranging method body into a
         * single line. This can be done only if the method body can be implemented by just a single line of code
         * (Not applicable if we are having multiple code lines here)
         * */
        Function<String, String> upperCaseName2 = name -> name.toUpperCase();

        /** Enhancing the implementation with method reference */
        Function<String, String> upperCaseName3 = String::toUpperCase;

        System.out.println(uppercaseName.apply("roman reigns"));

        /** We can have a UnaryOperator instead */
        UnaryOperator<String> upperCaseName4 = String::toUpperCase;

        System.out.println(upperCaseName4.apply("john cena"));

        /** When there are multiple lines in method body -------------------------------------------------------------*/
        Function<String, String> nameToUpperCase = name -> {
            if (Objects.isNull(name) || name.isBlank()) throw new IllegalArgumentException("invalid name");
            return name.toUpperCase();
        };

        BiFunction<Integer, Boolean, String> biFunctionLambdaImpl = (number, condition) -> {
            if (Objects.isNull(number) || Objects.isNull(condition)) {
                throw new RuntimeException("Both number and condition must not be null");
            }
            return "Output value is : " + (condition ? number * number : number + 1);
        };

        String output = biFunctionLambdaImpl.apply(10, Boolean.valueOf("tRuE"));
        System.out.println(output); // Output value is : 100
    }

    public static void noPrimitivesAllowed() {
        /** We cannot put primitive types as parameter types/ generic types of functional interfaces */
        Integer number = null;
        // int number = null; // This is an invalid declaration (Causes compile time error)

        // Function<int, double> myFunction = i -> Integer.valueOf(i).doubleValue(); // This will not work due to primitives
    }

    // Non-static/ instance declaration (this is the approach which is used the most)
    private Consumer<String> consumer = System.out::println;

    public static void main(String[] args) {
        LambdaExpressionsDemo.declarationAndPossibleSimplifications();

        new LambdaExpressionsDemo().consumer.accept("Hello world !");
    }
}
