package com.codewithnaman.java8;

import java.util.Arrays;
import java.util.List;

public class DeclarativeProgramming {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        numbers.forEach((Integer value) -> System.out.println(value));
    }
}
