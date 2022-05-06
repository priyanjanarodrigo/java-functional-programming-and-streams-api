package com.myorg.functionalProgramming.declarative.combinatorPattern;

import com.myorg.functionalProgramming.declarative.combinatorPattern.model.Customer;
import com.myorg.functionalProgramming.declarative.combinatorPattern.service.CustomerValidatorService;
import com.myorg.functionalProgramming.declarative.combinatorPattern.statics.ValidationResult;

import java.time.LocalDate;

import static com.myorg.functionalProgramming.declarative.combinatorPattern.CustomerRegistrationValidator.*;

public class CombinatorPatternDemo {
    public static void main(String[] args) {

        /** Traditional approach -------------------------------------------------------------------------------------*/

        // Valid customer scenario:

        Customer alice = new Customer("Alice", "alice@gmail.com", "+081233212332",
                LocalDate.of(2000, 1, 1));

        System.out.println(new CustomerValidatorService().isValidCustomer(alice)); // true

        // Invalid customer scenario:

        Customer bob = new Customer("Bob", "bob2gmail.com", "+081233212332",
                LocalDate.of(2000, 1, 1));

        /** In below case, even if the validation fails, we do not know what went wrong or what property is invalid*/
        System.out.println(new CustomerValidatorService().isValidCustomer(bob)); // false


        /** Combinator Pattern -----------------------------------------------------------------------------------------
         *
         * Combinator pattern is an awesome design pattern that allows us to chain functions together.
         * Basically a combinator is a function that might take all the functions as arguments and return new functions.
         * */

        // Valid customer scenario:
        Customer dwayneJohnson = new Customer(
                "Dwayne Johnson", "therock@wwe.com", "+081231112232",
                LocalDate.of(1983, 10, 02));

        ValidationResult result = isValidEmail()
                .and(isValidPhoneNumber())
                .and(isAdult())
                .apply(dwayneJohnson);

        System.out.println("ValidationResult : " + result);

        if (result != ValidationResult.SUCCESS) {
            throw new IllegalStateException(result.name());
        }

        // None of these stuff will run until we invoke .apply(Customer c)
        CustomerRegistrationValidator validator = isAdult()
                .and(isValidPhoneNumber());

        // invoking apply() method later on
        System.out.println("Lazy calling : "+validator.apply(dwayneJohnson));



        // Invalid customer scenario:
        Customer theMiz = new Customer(
                "Mizz", "mizz22@hollywood.com", "81231112232",
                LocalDate.of(1987, 2, 3));

        ValidationResult invalidResult = isValidEmail()
                .and(isValidPhoneNumber())
                .and(isAdult())
                .apply(theMiz);

        System.out.println("ValidationResult : " + invalidResult);

        if (invalidResult != ValidationResult.SUCCESS) {
            throw new IllegalStateException(invalidResult.name());
        }
    }
}
