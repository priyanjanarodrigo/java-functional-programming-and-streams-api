package com.myorg.functionalProgramming.declarative.functionalInterfaces.custom;

@FunctionalInterface
public interface MyAdditionInterface<T, U, R> {
    R getTotalOf(T t, U u);
}
