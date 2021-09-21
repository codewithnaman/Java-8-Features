package com.codewithnaman.java8.feature6;

import java.util.Optional;
import java.util.function.Consumer;

public class OptionalUsedCode {

    public static void main(String[] args) {
        OptionalUsedCode instance = new OptionalUsedCode();
        Consumer<Float> compareResult = e-> System.out.println(e.compareTo(1.5f)==0);
        Optional<Float> divisionResult = instance.divide(3,2);
        divisionResult.ifPresent(System.out::println);
        divisionResult.ifPresent(compareResult);
        divisionResult = instance.divide(2,0);
        divisionResult.ifPresent(System.out::println);
        divisionResult.ifPresent(compareResult);
        divisionResult = instance.divide(5,2);
        if(divisionResult.isPresent()){
            System.out.println(divisionResult.get());
        }
    }

    private Optional<Float> divide(int number, int divisor){
        if(divisor==0)
            return Optional.empty();
        return Optional.of((float)number/divisor);
    }
}
