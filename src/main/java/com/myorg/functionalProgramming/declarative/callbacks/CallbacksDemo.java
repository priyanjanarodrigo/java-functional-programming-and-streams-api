package com.myorg.functionalProgramming.declarative.callbacks;

import java.util.Objects;
import java.util.function.Consumer;

public class CallbacksDemo {
    public static void main(String[] args) {
        // Consumer callback example
        hello("Roman", null, value -> {
            System.out.println("No lastName provided for " + value);
        });

        // Runnable callback example
        hello("Michael", null, () -> System.out.println("lastName not provided"));
    }

    static void hello(String firstName, String lastName, Consumer<String> callback) {
        System.out.println(firstName);

        if (Objects.nonNull(lastName)) {
            System.out.println(lastName);
        } else {
            callback.accept(firstName);
        }
    }

    static void hello(String firstName, String lastName, Runnable runnableCallback) {
        System.out.println(firstName);
        if (Objects.nonNull(lastName)) {
            System.out.println(lastName);
        } else {
            runnableCallback.run();
        }
    }

    /**
     * javaScript callback example (Try executing on Chrome console):
     *
     *      function hello(firstName, lastName, callback) {
     *          console.log(firstName);
     *          if (lastName) {
     *              console.log(lastName);
     *          } else {
     *              callback();
     *          }
     *      }
     *
     *      hello("John",null,() => console.log("No last name provided"))
     * */
}
