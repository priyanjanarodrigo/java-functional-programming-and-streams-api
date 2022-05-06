package com.myorg.functionalProgramming.declarative.functionalInterfaces.preDefined.function;

import java.util.Optional;
import java.util.function.*;

/**
 * BiFunction accepts two parameters of provided data types and returns a value of provided output data type
 *      Example:
 *          BiFunction<String,Double,Integer>
 *              This by function will take one String parameter and one Double parameter and returns an Integer value
 **/
public class BiFunctionDemo {
    /**
     * Increments first parameter value by 1, and then multiplies by second parameter value. Following biFunction will
     * accept two Integer parameters and returns an Integer value.
     */
    static BiFunction<Integer, Integer, Integer> biFunction = (n1, n2) -> (n1 + 1) * n2;

    // imperative approach for biFunction above
    static int incrementBy1AndMultiplyByProvided(int number1, int number2) {
        return (number1 + 1) * number2;
    }

    static BiFunction<Integer, Integer, String> printBiFunction = (n1, n2) -> "Your output : " + String.valueOf(n1 + n2);

    static Function<String, String> stringFunction = s -> Optional.ofNullable(s).map(String::toUpperCase).orElse("VALUE_NOT_FOUND");

    public static void main(String[] args) {
        // Imperative approach
        System.out.println(incrementBy1AndMultiplyByProvided(1, 10));// 20

        // BiFunction related implementation
        System.out.println(biFunction.apply(1, 10));
        System.out.println(printBiFunction.apply(200, 300));

        /** Chaining 2 functions (a BiFunctions and then a Function)
         * ---------------------------------------------------------
         *      Step 1 : printBiFunction(1,2)
         *                  returns String output -> "Your output : 3"
         *      Step 2 : stringFunction
         *                   returns String output ->  "YOUR OUTPUT : 3"
         * */
        String output = printBiFunction
                .andThen(stringFunction)
                .apply(1, 2);

        System.out.println(output); // YOUR OUTPUT : 3

        // pre-defined variations of BiFunction ------------------------------------------------------------------------

        ToDoubleBiFunction<Integer, Long> toDoubleBiFunction = (i, l) -> i * l;
        System.out.println(toDoubleBiFunction.applyAsDouble(Integer.valueOf(1), 32L));

        ToLongBiFunction<Integer, String> toLongBiFunction = (i, s) -> i * (Integer.valueOf(s));
        System.out.println(toLongBiFunction.applyAsLong(1, "200"));

        ToIntBiFunction<Double, Long> toIntBiFunction = (d, l) -> Double.valueOf(d).intValue() + Long.valueOf(l).intValue();
        System.out.println(toIntBiFunction.applyAsInt(123.22d, 200L));

        // BinaryOperators ---------------------------------------------------------------------------------------------

        /**  BinaryOperator<T> extends BiFunction<T,T,T> : Represents an operation upon two operands of the same type,
         *  producing a result of the same type as the operands*/
        BinaryOperator<Integer> binaryOperator = (i1, i2) -> (i1 + i2) * 10;
        System.out.println(binaryOperator.apply(1,2));// 30

        IntBinaryOperator intBinaryOperator = (i1, i2) -> i1 + i2;
        System.out.println(intBinaryOperator.applyAsInt(2, 2));

        DoubleBinaryOperator doubleBinaryOperator = (d1, d2) -> d1 + d2;
        System.out.println(doubleBinaryOperator.applyAsDouble(12.2d, 2343.34D));

        LongBinaryOperator longBinaryOperator = (l1, l2) -> l1 + l2;
        System.out.println(longBinaryOperator.applyAsLong(12323L, 1234213L));
    }
}
