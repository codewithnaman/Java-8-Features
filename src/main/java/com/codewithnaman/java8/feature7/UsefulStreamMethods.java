package com.codewithnaman.java8.feature7;

import java.util.*;
import java.util.stream.IntStream;

public class UsefulStreamMethods {

    public static void main(String[] args) {
        List<Person> persons = PersonsListBuilder.getPersonsList();
        persons.stream().map(Person::getName).forEach(System.out::println);
        IntStream personsAgeStream = persons.stream().mapToInt(Person::getAge);
        OptionalInt minimumAge = personsAgeStream.min();
        minimumAge.ifPresent(System.out::println); //20
        personsAgeStream = persons.stream().mapToInt(Person::getAge);
        OptionalInt maximumAge = personsAgeStream.max();
        maximumAge.ifPresent(System.out::println); //38
        personsAgeStream = persons.stream().mapToInt(Person::getAge);
        IntSummaryStatistics summaryStatistics = personsAgeStream.summaryStatistics();
        System.out.println(summaryStatistics); //IntSummaryStatistics{count=13, sum=371, min=20, average=28.538462, max=38}

        System.out.println(persons.stream().filter(e->e.getAge()>30).count());
        System.out.println(persons.stream().allMatch(e->e.getAge()<50));
        System.out.println(persons.stream().anyMatch(e->e.getAge()>50));

        Map<String,List<String>> synonyms = new HashMap<>();
        List<String> angerSynonymList = new ArrayList<>();
        angerSynonymList.add("Enrage");
        angerSynonymList.add("Infuriate");
        angerSynonymList.add("Nettle");
        synonyms.put("Anger",angerSynonymList);
        List<String> answerSynonymList = new ArrayList<>();
        answerSynonymList.add("Reply");
        answerSynonymList.add("Respond");
        answerSynonymList.add("Retort");
        synonyms.put("Answer",answerSynonymList);

        synonyms.values().stream().forEach(System.out::println);
        synonyms.values().stream().flatMap(e-> e.stream()).forEach(System.out::println);
    }
}
