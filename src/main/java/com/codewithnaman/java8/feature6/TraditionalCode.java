package com.codewithnaman.java8.feature6;

import java.util.Objects;

public class TraditionalCode {

    public static void main(String[] args) {
        TraditionalCode instance = new TraditionalCode();
        Float divisionResult = instance.divide(3,2);
        System.out.println(divisionResult);
        System.out.println(divisionResult.compareTo(Float.valueOf(1.5f)));
        divisionResult = instance.divide(2,0);
        System.out.println(divisionResult);
        if(Objects.nonNull(divisionResult))
            System.out.println(divisionResult.compareTo(Float.valueOf(1.5f)));
    }

    private Float divide(int number,int divisor){
        if(divisor==0)
            return null;
        return (float)number/divisor;
    }
}
