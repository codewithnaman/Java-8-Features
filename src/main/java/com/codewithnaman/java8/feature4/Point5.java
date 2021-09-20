package com.codewithnaman.java8.feature4;

interface Expandable {
    default void expand(){
        System.out.println("Expanding because of Expandable property");
    }
}
interface Gas{
    default void expand(){
        System.out.println("Expanding because of Gas State Property");
    }
}
public class Point5 implements Expandable,Gas {

    @Override
    public void expand() {
        Gas.super.expand();
    }

    public static void main(String[] args) {
        Point5 point = new Point5();
        point.expand();
    }
}
