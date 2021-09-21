package com.codewithnaman.java8.feature7;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DeclarativeProgrammingMethodSortByAge {

    public static void main(String[] args) {
        List<Person> persons = PersonsListBuilder.getPersonsList();
        //Print list of persons
        persons.forEach(System.out::println);
        //Sort by increasing age
        System.out.println("Sort by Age Increasing Order");
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.getAge(),o2.getAge());
            }
        });
        persons.forEach(System.out::println);
        //Sort by decreasing age
        System.out.println("Sort by Age Decreasing Order");
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o2.getAge(),o1.getAge());
            }
        });
        persons.forEach(System.out::println);
        //Sort by age if age are equal then sort by name
        System.out.println("Sort by Age Decreasing Order");
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if(o1.getAge() == o2.getAge())
                    return o1.getName().compareTo(o2.getName());
                return Integer.compare(o1.getAge(),o2.getAge());
            }
        });
        persons.forEach(System.out::println);
    }
}
