package com.codewithnaman.java8.feature1;

import java.util.Arrays;
import java.util.List;

public class LambadaExpressionImplementation {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbers.forEach(value -> System.out.println(value));
    }
}
