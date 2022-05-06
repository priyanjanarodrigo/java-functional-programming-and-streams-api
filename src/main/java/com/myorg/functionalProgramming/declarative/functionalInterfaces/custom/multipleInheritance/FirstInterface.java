package com.myorg.functionalProgramming.declarative.functionalInterfaces.custom.multipleInheritance;

public interface FirstInterface {
    default void displayMessage() {
        System.out.println("Hello from FirstInterface !");
    }
}
