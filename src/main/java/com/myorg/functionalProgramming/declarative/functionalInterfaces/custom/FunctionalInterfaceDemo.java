package com.myorg.functionalProgramming.declarative.functionalInterfaces.custom;

public class FunctionalInterfaceDemo {
    public static void main(String[] args) {

        /**
         * Providing an implementation to the single abstract method (convertToUpperCase) in MyFunctionalInterface
         * via a lambda expression. In this case we do not need to implement the functional interface in order to use
         * single abstract method
         * */
        MyFunctionalInterface mfi = s -> s.toUpperCase();
        System.out.println(mfi.convertToUpperCase("hello functional interfaces"));

        // Using static methods available in functional interface
        MyFunctionalInterface.display("Hi HOW are you");
        System.out.println(MyFunctionalInterface.convertToLowercase("HELLO WORLD"));


        // Custom functional interface implementation with generics
        MyAdditionInterface<Integer, Integer, Integer> additionInterface = (n1, n2) -> n1 + n2;
        System.out.println(additionInterface.getTotalOf(10, 2));
    }
}
