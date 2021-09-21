package com.codewithnaman.java8.feature7;

import java.util.function.Predicate;

public class UsefulFunctionalInterfaceMethods {

    public static void main(String[] args) {
        Predicate<Integer> divisibleByTwo = number -> number%2==0;
        System.out.println(divisibleByTwo.test(5));
        System.out.println(divisibleByTwo.negate().test(5));
        Predicate<Integer> divisibleByFive = number -> number%5==0;
        System.out.println(divisibleByFive.or(divisibleByTwo).test(4));
        System.out.println(divisibleByFive.or(divisibleByTwo).test(7));
        System.out.println(divisibleByFive.and(divisibleByTwo).test(10));
        System.out.println(divisibleByFive.and(divisibleByTwo).test(8));
    }
}
