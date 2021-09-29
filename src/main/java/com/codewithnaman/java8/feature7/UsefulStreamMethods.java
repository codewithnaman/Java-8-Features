package com.codewithnaman.java8.feature7;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        System.out.println("======Concat======");
        List<String> stream1 = new ArrayList<>();
        stream1.add("Apple");
        stream1.add("Mango");
        List<String> stream2 = new ArrayList<>();
        stream2.add("BMW");
        stream2.add("Mercedes");
        Stream.concat(stream1.stream(),stream2.stream()).forEach(System.out::println);
        System.out.println("=====Zip=====");
        List<Integer> list1 = Arrays.asList(1,2,3,4,5);
        List<String> list2 = Arrays.asList("a","b","c","d","e");
        IntStream.range(0,Math.min(list1.size(),list2.size()))
                .mapToObj(i-> new String[] {list1.get(i).toString(),list2.get(i)})
                .forEach(element -> System.out.println(element[0]+","+element[1]));
    }
}
