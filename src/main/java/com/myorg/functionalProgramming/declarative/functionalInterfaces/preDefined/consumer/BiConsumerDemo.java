package com.myorg.functionalProgramming.declarative.functionalInterfaces.preDefined.consumer;

import com.myorg.functionalProgramming.common.model.Customer;

import java.util.function.BiConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;

public class BiConsumerDemo {

    // Simply BiConsumer accepts two parameters of particular data type and consumes without returning anything
    static BiConsumer<Integer, String> biConsumer = (i, s) -> System.out.println("message is :" + s + " , number is : " + i);

    static BiConsumer<Customer, Boolean> customerBooleanBiConsumer = (customer, showPhoneNumber) -> {
        System.out.println("Hello " + customer.getName()
                + ", thanks for registering phone number " + (showPhoneNumber ? customer.getPhoneNumber() : "***********"));
    };

    public static void main(String[] args) {
        biConsumer.accept(1, "Hello how are you ? ");

        Customer customer1 = new Customer("Roman Reings", "1192939393");
        Customer customer2 = new Customer("Dwayne Johnson", "11990099332");

        customerBooleanBiConsumer.accept(customer1, true);
        customerBooleanBiConsumer.accept(customer2, false);

        // pre-defined variations of BiConsumer ------------------------------------------------------------------------

        ObjIntConsumer<Customer> objIntConsumer = (c, i) -> System.out.println(
                "customer : " + c + ", reference number: " + i);
        objIntConsumer.accept(customer1, 123444);

        ObjDoubleConsumer<Customer> objDoubleConsumer = (c, d) -> System.out.println(
                "Hi " + c.getName() + ", your percentage is : " + d + "%");
        objDoubleConsumer.accept(customer2, 99);

        ObjLongConsumer<String> objLongConsumer = (s, l) -> System.out.println(
                "Value is: " + l + " and message is: " + s);
        objLongConsumer.accept("hello there", 111222333);
    }
}
