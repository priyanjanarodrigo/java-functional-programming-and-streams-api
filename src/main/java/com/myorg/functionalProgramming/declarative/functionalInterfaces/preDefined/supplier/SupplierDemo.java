package com.myorg.functionalProgramming.declarative.functionalInterfaces.preDefined.supplier;

import java.util.function.*;

public class SupplierDemo {

    // Imperative approach
    static String getDBConnectionUrl() {
        return "jdbc://localhost:5432/user_db";
    }

    // Supplier represents a supplier result. It does not receive any parameters
    static Supplier<String> supplier = () -> "jdbc://localhost:5432/user_db";

    public static void main(String[] args) {
        System.out.println(getDBConnectionUrl());
        System.out.println(supplier.get());

        // pre-defined variations of Supplier --------------------------------------------------------------------------

        IntSupplier intSupplier = () -> Integer.valueOf(12);
        System.out.println(intSupplier.getAsInt());

        DoubleSupplier doubleSupplier = () -> Double.valueOf("12321.232d");
        System.out.println(doubleSupplier.getAsDouble());

        LongSupplier longSupplier = () -> Integer.valueOf(123123).longValue();
        System.out.println(longSupplier.getAsLong());

        BooleanSupplier booleanSupplier = () -> Boolean.valueOf("tRuE");
        System.out.println(booleanSupplier.getAsBoolean());
    }
}
