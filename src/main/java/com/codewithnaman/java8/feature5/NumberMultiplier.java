package com.codewithnaman.java8.feature5;

interface Multiplier{
    int multiplier(int value);
}

public class NumberMultiplier {
    public static void main(String[] args) {
        NumberMultiplier numberMultiplier = new NumberMultiplier();
        Multiplier multiplier = numberMultiplier.getMultiplier(5);
        System.out.println(multiplier.multiplier(3));
    }

    private Multiplier getMultiplier(int multiplyBy) {
        return value -> value*multiplyBy;
    }
}
