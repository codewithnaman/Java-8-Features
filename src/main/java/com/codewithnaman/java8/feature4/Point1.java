package com.codewithnaman.java8.feature4;

interface HelloTest {
    default void printHello(){
        System.out.println("Hello");
    }
}

public class Point1 implements HelloTest {
    @Override
    public void printHello() {
        System.out.println("Hello From Class");
    }

    public static void main(String[] args) {
        Point1 point = new Point1();
        point.printHello();
    }
}
