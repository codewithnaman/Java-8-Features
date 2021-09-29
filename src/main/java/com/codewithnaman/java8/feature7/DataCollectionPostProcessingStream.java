package com.codewithnaman.java8.feature7;

import java.util.*;
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

        Map<Integer,List<Person>> personsByTheirAge = persons.stream().collect(Collectors.groupingBy(Person::getAge));
        //{32=[Person{name='Marshall', age=32}, Person{name='Victoria', age=32}], 20=[Person{name='Annabelle', age=20}],
        // 38=[Person{name='Barney', age=38}], 25=[Person{name='Monica', age=25}, Person{name='Joey', age=25}, Person{name='Tracy', age=25}],
        // 28=[Person{name='Ted', age=28}, Person{name='Chandler', age=28}, Person{name='Lily', age=28}],
        // 30=[Person{name='Rachel', age=30}, Person{name='Ross', age=30}, Person{name='Robin', age=30}]}
        System.out.println(personsByTheirAge);

        Map<Character,List<Person>> personsByTheirFirstLetterOfName = persons.stream()
                .collect(Collectors.groupingBy(e->e.getName().charAt(0)));
        //{A=[Person{name='Annabelle', age=20}], B=[Person{name='Barney', age=38}],
        // R=[Person{name='Rachel', age=30}, Person{name='Ross', age=30}, Person{name='Robin', age=30}],
        // C=[Person{name='Chandler', age=28}], T=[Person{name='Ted', age=28}, Person{name='Tracy', age=25}],
        // V=[Person{name='Victoria', age=32}], J=[Person{name='Joey', age=25}], L=[Person{name='Lily', age=28}],
        // M=[Person{name='Monica', age=25}, Person{name='Marshall', age=32}]}
        System.out.println(personsByTheirFirstLetterOfName);

        Map<Character, List<Integer>> charAge =
                persons
                        .stream()
                        .collect(Collectors.groupingBy(person -> person.getName().charAt(0),
                                Collectors.mapping(person-> person.getAge(),Collectors.toList())));
        //{A=[20], B=[38], R=[30, 30, 30], C=[28], T=[28, 25], V=[32], J=[25], L=[28], M=[25, 32]}
        System.out.println(charAge);

        Map<Character, Optional<Person>> charEldest =
                persons
                        .stream()
                        .collect(Collectors.groupingBy(person -> person.getName().charAt(0),
                                Collectors.maxBy(Comparator.comparingInt(Person::getAge))));
        //{A=Optional[Person{name='Annabelle', age=20}], B=Optional[Person{name='Barney', age=38}],
        // R=Optional[Person{name='Rachel', age=30}], C=Optional[Person{name='Chandler', age=28}],
        // T=Optional[Person{name='Ted', age=28}], V=Optional[Person{name='Victoria', age=32}],
        // J=Optional[Person{name='Joey', age=25}], L=Optional[Person{name='Lily', age=28}],
        // M=Optional[Person{name='Marshall', age=32}]}
        System.out.println(charEldest);

        Map<Integer,Optional<Person>> personByAgeWithHighestNameLength = persons
                .stream()
                .collect(Collectors.groupingBy(
                        Person::getAge,Collectors.reducing((p1,p2)-> {
                            if(p1.getAge()==p2.getAge())
                                return p1.getName().length()>p2.getName().length() ? p1 : p2;
                            else
                                return p1.getAge()> p2.getAge() ? p1: p2;
                        })
                ));
        //{32=Optional[Person{name='Victoria', age=32}], 20=Optional[Person{name='Annabelle', age=20}],
        // 38=Optional[Person{name='Barney', age=38}], 25=Optional[Person{name='Monica', age=25}],
        // 28=Optional[Person{name='Chandler', age=28}], 30=Optional[Person{name='Rachel', age=30}]}
        System.out.println(personByAgeWithHighestNameLength);
    }
}
