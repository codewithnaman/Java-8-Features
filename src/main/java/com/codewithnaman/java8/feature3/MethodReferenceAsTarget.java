package com.codewithnaman.java8.feature3;

import java.util.Arrays;
import java.util.List;

public class MethodReferenceAsTarget {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Test", 1),
                new Person("Test1", 2)
        );

        persons.forEach(Person::printPersonInformation);
    }
}
