package com.myorg.functionalProgramming.common.model;

public class Customer {
    private final String name;

    private final String phoneNumber;

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer = { name: " + this.name + ", phoneNumber: " + this.phoneNumber + " }";
    }
}
