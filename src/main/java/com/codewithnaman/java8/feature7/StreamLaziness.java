package com.codewithnaman.java8.feature7;

import java.util.Comparator;
import java.util.List;

public class StreamLaziness {
    public static void main(String[] args) {
        List<Person> persons = PersonsListBuilder.getPersonsList();
        System.out.println("List Size is "+persons.size());
        persons.stream()
                .filter(person -> {
                    System.out.println("Filtering age 30, Current Person age is "+person.getAge());
                    return person.getAge() == 30;
                });
        persons.stream().sorted(Comparator.comparing(Person::getName))
                .filter(person -> {
                    System.out.println("Filtering age 28, Current Person age is "+person.getAge()
                            +" and Name is "+person.getName());
                    return person.getAge() == 28;
                }).findFirst().ifPresent(System.out::println);
    }
}
