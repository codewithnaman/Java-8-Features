package com.codewithnaman.java8.feature4;

interface PrintHello{
    default void print(){
        System.out.println("Hello");
    }
}

class PrintWorld {
    public void print(){
        System.out.println("World");
    }
}

public class Point3And4 extends PrintWorld implements PrintHello {
    public static void main(String[] args) {
        Point3And4 point3And4 = new Point3And4();
        point3And4.print();
    }
}
