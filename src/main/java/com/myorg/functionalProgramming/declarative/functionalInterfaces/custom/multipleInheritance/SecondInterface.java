package com.myorg.functionalProgramming.declarative.functionalInterfaces.custom.multipleInheritance;

public interface SecondInterface {
    default void displayMessage() {
        System.out.println("Hello from SecondInterface !");
    }
}
