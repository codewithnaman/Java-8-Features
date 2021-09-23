package com.codewithnaman.java8.feature7;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DataCollectionPostProcessingStream {
    public static void main(String[] args) {
        List<Person> persons = PersonsListBuilder.getPersonsList();
        List<Person> sortedByAgePersonsList = persons.stream()
                .sorted(Comparator.comparingInt(Person::getAge)).collect(Collectors.toList());
        sortedByAgePersonsList.forEach(System.out::println);
        Set<Person> sortedByAgePersonsSet = persons.stream()
                .filter(e-> e.getAge()>25).collect(Collectors.toSet());
        System.out.println(sortedByAgePersonsSet.size());
    }
}
