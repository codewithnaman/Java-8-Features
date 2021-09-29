package com.codewithnaman.java8.feature7;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NewMapFunctions {

    public static void main(String[] args) {
        Map<String,Integer> mapOfFruitsCount = new HashMap<>();
        mapOfFruitsCount.put("Apple",2);
        mapOfFruitsCount.put("Mango",1);
        mapOfFruitsCount.put("Guava",5);
        //{Guava=5, Apple=2, Mango=1}
        System.out.println(mapOfFruitsCount);
        mapOfFruitsCount.compute("Apple",(key,value)->value+1);
        // mapOfFruitsCount.compute("Banana",(key,value)->value+1); -- will throw NPE
        //{Guava=5, Apple=3, Mango=1}
        System.out.println(mapOfFruitsCount);
        mapOfFruitsCount.compute("Banana",(key,value)-> Objects.nonNull(value) ? value+1 : 1);
        System.out.println(mapOfFruitsCount);
        mapOfFruitsCount.merge("Mango",2,(value,defaultValue)->value+defaultValue);
        mapOfFruitsCount.merge("Pomegranate",1,(value,defaultValue)->value+defaultValue);
        //{Guava=5, Apple=3, Mango=3, Pomegranate=1, Banana=1}
        System.out.println(mapOfFruitsCount);
        mapOfFruitsCount.computeIfPresent("Apple",(key,value)->value+2);
        mapOfFruitsCount.computeIfPresent("Papaya",(key,value)->value+2);
        //{Guava=5, Apple=5, Mango=3, Pomegranate=1, Banana=1}
        System.out.println(mapOfFruitsCount);
        mapOfFruitsCount.computeIfAbsent("Apple",(key)->1);
        mapOfFruitsCount.computeIfAbsent("Papaya",(key)->1);
        //{Guava=5, Apple=5, Papaya=1, Mango=3, Pomegranate=1, Banana=1}
        System.out.println(mapOfFruitsCount);
    }
}
