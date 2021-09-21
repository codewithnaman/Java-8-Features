package com.codewithnaman.java8.feature7;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonsListBuilder {

    public static List<Person> getPersonsList(){
        List<Person> names = new ArrayList<>();
        names.add(new Person("Annabelle",20));
        names.add(new Person("Monica",25));
        names.add(new Person("Rachel",30));
        names.add(new Person("Barney",38));
        names.add(new Person("Ted",28));
        names.add(new Person("Joey",25));
        names.add(new Person("Chandler",28));
        names.add(new Person("Ross",30));
        names.add(new Person("Lily",28));
        names.add(new Person("Marshall",32));
        names.add(new Person("Robin",30));
        names.add(new Person("Tracy",25));
        names.add(new Person("Victoria",32));
        return names;
    }
}

class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getName().equals(person.getName()) && getAge().equals(person.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge());
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
