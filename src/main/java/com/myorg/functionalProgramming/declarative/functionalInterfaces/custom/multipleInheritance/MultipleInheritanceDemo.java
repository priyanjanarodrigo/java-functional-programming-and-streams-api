package com.myorg.functionalProgramming.declarative.functionalInterfaces.custom.multipleInheritance;

public class MultipleInheritanceDemo implements FirstInterface, SecondInterface {

    /**
     * Both the FirstInterface and SecondInterface contains "displayMessage()" default method. Importantly
     * this class implements both of them. With respect to that scenario, here's how we can achieve multiple inheritance
     * concept. We must override "displayMethod()" message and, then we can point to any of the interface as follows.
     *
     * Or else we can have our own implementation rather than using one of the implementation from an interface
     */
    @Override
    public void displayMessage() {
        // Points to FirstInterface default method implementation
        FirstInterface.super.displayMessage();

        //Points to FirstInterface default method implementation
        // SecondInterface.super.displayMessage();;
    }

    public static void main(String[] args) {
        new MultipleInheritanceDemo().displayMessage();
    }
}
