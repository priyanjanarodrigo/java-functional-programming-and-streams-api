package com.myorg.functionalProgramming.declarative.functionalInterfaces.preDefined.function;

import java.util.function.*;

public class FunctionDemo {
    // declarative approach (Function<T,U> interface)
    static Function<Integer, Integer> incrementByOneFunction = number -> number + 1;

    static Function<Integer, Integer> multiplyBy10Function = n -> n * 10;

    // imperative approach
    static int increment(int number) {
        return (number + 1);
    }

    public static void main(String[] args) {
        int increment = FunctionDemo.increment(1);
        System.out.println(increment);

        // Calling Function interface implemented by a lambda expression
        int incrementedValue = incrementByOneFunction.apply(1);
        System.out.println(incrementedValue);

        int multipliedValue = multiplyBy10Function.apply(2);
        System.out.println(multipliedValue);

        // Function chaining -> Combining multiple Functions to achieve a desired output/state
        int add1andMultiplyBy10Value = incrementByOneFunction
                .andThen(multiplyBy10Function)
                .apply(1);

        /** Above implementation follows these steps
         *      Step 1 : 1+1 = 2 --> return 2 by incrementByOneFunction
         *      Step 2 : 2 * 10 = 20 --> return 20 by multiplyBy10Function
         * */
        System.out.println(add1andMultiplyBy10Value); // 20


        // pre-defined variations of Function --------------------------------------------------------------------------

        ToIntFunction<String> toIntFunction = s -> Integer.valueOf(s);
        System.out.println(toIntFunction.applyAsInt("432"));

        ToLongFunction<Integer> toLongFunction = i -> i * i * i * i * i;
        System.out.println(toLongFunction.applyAsLong(100));

        ToDoubleFunction<String> toDoubleFunction = s -> Double.valueOf(s);
        System.out.println(toDoubleFunction.applyAsDouble("222.12d"));

        IntToDoubleFunction intToDoubleFunction = i -> Double.valueOf(String.valueOf(i));
        System.out.println(intToDoubleFunction.applyAsDouble(34));

        IntToLongFunction intToLongFunction = i -> Long.valueOf(String.valueOf(i));
        System.out.println(intToLongFunction.applyAsLong(44));

        LongToDoubleFunction longToDoubleFunction = l -> Long.valueOf(l).doubleValue();
        System.out.println(longToDoubleFunction.applyAsDouble(10000L));

        LongToIntFunction longToIntFunction = l -> Long.valueOf(l).intValue();
        System.out.println(longToIntFunction.applyAsInt(1222));

        DoubleToIntFunction doubleToIntFunction = d -> Double.valueOf(d).intValue();
        System.out.println(doubleToIntFunction.applyAsInt(2.3D));

        DoubleToLongFunction doubleToLongFunction = d -> Double.valueOf(d).longValue();
        System.out.println(doubleToLongFunction.applyAsLong(132.2d));

        // UnaryOperators ----------------------------------------------------------------------------------------------

        /** UnaryOperator<T> extends Function<T, T> : Represents an operation on a single operand that produces a
         * result of the same type as its operand.*/
        UnaryOperator<String> unaryOperator = s -> s.toUpperCase() + " - OUTPUT";
        System.out.println(unaryOperator.apply("hello"));

        IntUnaryOperator intUnaryOperator = i -> (i * i) + 2;
        System.out.println(intUnaryOperator.applyAsInt(1));

        DoubleUnaryOperator doubleUnaryOperator = d -> d*2.5d;
        System.out.println(doubleUnaryOperator.applyAsDouble(2.5D));

        LongUnaryOperator longUnaryOperator = l -> l*l;
        System.out.println(longUnaryOperator.applyAsLong(1000));
    }
}
