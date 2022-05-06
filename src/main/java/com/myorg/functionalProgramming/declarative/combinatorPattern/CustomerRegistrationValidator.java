package com.myorg.functionalProgramming.declarative.combinatorPattern;

import com.myorg.functionalProgramming.declarative.combinatorPattern.model.Customer;
import com.myorg.functionalProgramming.declarative.combinatorPattern.statics.ValidationResult;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.function.Function;

import static com.myorg.functionalProgramming.declarative.combinatorPattern.statics.ValidationResult.*;

public interface CustomerRegistrationValidator extends Function<Customer, ValidationResult> {

    static CustomerRegistrationValidator isValidEmail() {
        return customer -> Objects.nonNull(customer.getEmail()) && customer.getEmail().contains("@")
                ? SUCCESS : EMAIL_NOT_VALID;
    }

    static CustomerRegistrationValidator isValidPhoneNumber() {
        return customer -> Objects.nonNull(customer.getPhoneNumber()) && customer.getPhoneNumber().startsWith("+0")
                ? SUCCESS : PHONE_NUMBER_NOT_VALID;
    }

    static CustomerRegistrationValidator isAdult() {
        return customer -> Period.between(customer.getDateOfBirth(), LocalDate.now()).getYears() > 16
                ? SUCCESS : IS_NOT_AN_ADULT;
    }

    /**
     * Combinator pattern allows us to chain all the functions here
     */

    default CustomerRegistrationValidator and(CustomerRegistrationValidator customerRegistrationValidator) {
        return customer -> {
            ValidationResult result = this.apply(customer);
            return result.equals(SUCCESS) ? customerRegistrationValidator.apply(customer) : result;
        };
    }
}
