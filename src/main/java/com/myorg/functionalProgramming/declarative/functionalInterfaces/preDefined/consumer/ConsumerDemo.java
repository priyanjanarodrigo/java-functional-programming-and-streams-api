package com.myorg.functionalProgramming.declarative.functionalInterfaces.preDefined.consumer;

import com.myorg.functionalProgramming.common.model.Customer;

import java.util.function.*;

public class ConsumerDemo {

    // Imperative method implementation
    static void greetCustomer(Customer customer) {
        System.out.println("Hello " + customer.getName()
                + ", thanks for registering phone number " + customer.getPhoneNumber());
    }

    /**
     * Consumer does not return anything but consumes the provided type of value.
     * Following is the declarative approach based Consumer implementation that can be used instead of above
     * imperative implementation
     */
    static Consumer<Customer> customerConsumer = c -> System.out.println(
            "Hello " + c.getName() + ", thanks for registering phone number " + c.getPhoneNumber());

    public static void main(String[] args) {
        Customer customer = new Customer("John Cena", "0778299112");

        greetCustomer(customer); // Calling the imperative approach based method
        customerConsumer.accept(customer); // Calling  the consumer implementation

        // pre-defined variations of Consumer --------------------------------------------------------------------------

        IntConsumer intConsumer = i -> System.out.println("Value is : " + i);
        intConsumer.accept(1);

        DoubleConsumer doubleConsumer = d -> System.out.println(d * 2);
        doubleConsumer.accept(12.23D);

        LongConsumer longConsumer = l -> System.out.println(Long.valueOf(l).intValue());
        longConsumer.accept(123123123);
    }
}
