package com.codewithnaman.java8;

import java.util.Arrays;
import java.util.List;

public class ImperativeProgramming {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }
    }
}
