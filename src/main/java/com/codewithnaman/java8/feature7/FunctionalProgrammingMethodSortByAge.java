package com.codewithnaman.java8.feature7;

import java.util.Comparator;
import java.util.List;

public class FunctionalProgrammingMethodSortByAge {
    public static void main(String[] args) {
        List<Person> persons = PersonsListBuilder.getPersonsList();
        //Print list of persons
        persons.forEach(System.out::println);
        //Sort by increasing age
        System.out.println("Sort by Age Increasing Order");
        Comparator<Person> ageInIncreasingOrder = (p1, p2) -> Integer.compare(p1.getAge(), p2.getAge());
        persons.stream().sorted(ageInIncreasingOrder).forEach(System.out::println);
        System.out.println("Or Using ComparingInt");
        persons.stream().sorted(Comparator.comparingInt(Person::getAge)).forEach(System.out::println);
        //Sort by decreasing age
        System.out.println("Sort by Age Decreasing Order");
        persons.stream().sorted(ageInIncreasingOrder.reversed()).forEach(System.out::println);
        //Sort by age if age are equal then sort by name
        System.out.println("Sort by Age Decreasing Order");
        persons.stream()
                .sorted(ageInIncreasingOrder.thenComparing(Person::getName))
                .forEach(System.out::println);
    }
}
