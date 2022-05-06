package com.myorg.functionalProgramming.declarative.combinatorPattern.service;

import com.myorg.functionalProgramming.declarative.combinatorPattern.model.Customer;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class CustomerValidatorService {

    private static final String EMPTY_SPACE = " ";

    private boolean isValidEmail(String email) {
        return Objects.nonNull(email) && email.contains("@");
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return Objects.nonNull(phoneNumber) && phoneNumber.startsWith("+0");
    }

    private boolean isAdult(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears() > 16;
    }

    /**
     * Based on this approach, we have to add more methods and put inside isValidCustomer, if there are more
     * validations required.
     *
     * And also in case if we need different validations (ex: validation for all, validation for phoneNumber and email
     * only), we have to implement more methods. But that approach is also kind of a
     * duplication of same logic
     */

    // Validates all
    public boolean isValidCustomer(Customer customer) {
        return isValidEmail(customer.getEmail())
                && isValidPhoneNumber(customer.getPhoneNumber())
                && isAdult(customer.getDateOfBirth());
    }

    // Validates only the phoneNumber and email
    public boolean isValidPhoneNumberAndEmail(Customer customer) {
        return isValidEmail(customer.getEmail())
                && isValidPhoneNumber(customer.getPhoneNumber());
    }
}
