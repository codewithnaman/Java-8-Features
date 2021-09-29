# Java 8 Features and Examples
## Table of Contents
* [Feature 0 - Functional Interfaces](#feature-0---functional-interfaces)
* [Feature 1 - Lambada expression](#feature-1---lambada-expression)
* [Feature 2 - Writing your own functional interface](#feature-2---writing-your-own-functional-interface)
* [Feature 3 - Method References](#feature-3---method-references)
* [Feature 4 - Default method in Interfaces](#feature-4---default-method-in-interfaces)
* [Feature 5 - Final and effectively final variable](#feature-5---final-and-effectively-final-variable)
* [Feature 6 - Optional](#feature-6---optional)
* [Feature 7 - Streams](#feature-7---streams)
* [Java 8 Lambda Expression Best Practices](#java-8-lambda-expression-best-practices)

Java 8 introduces declarative style or functional style of programming over the imperative programming. Let's first
understand what is declarative programming and imperative programming.

### Imperative programming
In this style of programming we define each and every detail to compiler like what will be control flow and what operation
need to perform on the data. Let's take an example:
```java
public class ImperativeProgramming {
    public static void main(String[] args){
      List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
      for (int i = 0; i < numbers.size(); i++) {
         System.out.println(numbers.get(i));
      }
    }
}
```
In above program we have data variable numbers; over which we want to iterate and perform some operation. In above program,
we are defining from where iteration starts and where it ends, and then we are performing actual operation on our data.
This style of programming where you define yourself control flow+operation on data is an example of imperative programming.

### Declarative programming
In declarative programming we leave the control to compiler, and we specify what operation we want to perform. Let's
transform above example to declarative programming style.
```java
public class DeclarativeProgramming {
    public static void main(String[] args){
      List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
      numbers.forEach((Integer value) -> System.out.println(value));
    }
}
```
In above example we left the iteration logic or control flow logic on the compiler, and created an anonymous function 
which just specifying the actual operation we want to perform on data. We are not specifying any iteration or control 
flow logic.
This anonymous function we used is implementation for Consumer interface comes in java.util.stream package in Java 8.

In One line I can define imperative and declarative programming like below:
```text
“Imperative programming is like how you do something, and declarative programming is more like what you do.”
```

## Major Features of Java 8
### Feature 0 - Functional Interfaces
In Previous versions of Java below 8; To create a concrete implementation of interface, we implement interface in classes
or create anonymous class and pass it to function or assign to variable. In Java 8 We get Lambada Expression; Where we 
can define or implement an interface without creating anonymous classes and provide implementation in same line where 
you are declaring variable. 

A functional interface is useful to create and assign a lambada expression. To create a functional interface the 
interface should qualify below properties:
* It should contain only one abstract method
* Interface can have default methods
* Interface can have static methods
* Optionally we can mark the @FunctionalInterface annotation on Interface

We will talk about default method and static method in upcoming section. 

There are some interfaces which already qualify the above criteria in previous version of Java Below 8; Some examples 
are below:
* Runnable
* Callable
* Comparable

In Java 8, We get few new functional interfaces; which help for carry out most of the tasks.
* Consumer\<U>
* Supplier\<U>
* Function\<U,R>
* Predicate\<T>
* BiFunction\<T, U, R>
* BiConsumer\<T, U>

### Feature 1 - Lambada expression
As We discussed in last feature section that Functional Interface use for defining and assigning lambada expression. In
this section we will se how lambada expression bring shift in writing the declarative style of programming over iterative
programming.

Let's get started; we will take a very basic example where we want to print list of numbers.In Java version below 8 we 
write the code like below for this:
```java
public class TraditionalMethod {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }
    }
}
```

or With Java 7 for each loop like below:

```java
public class TraditionalMethod {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        for (int number : numbers) {
            System.out.println(number);
        }
    }
}
```
In above examples we are defining the iteration process of the collection. Let's have a look in Java 8 style of 
programming where we just focus on logic of printing the numbers; not on how control flows. Let's see below example:

**Method 1:**
```java
public class ModernMethod {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbers.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer value) {
                System.out.println(value);
            }
        });
    }
}
```
The above code is more verbose where we are calling a method on the collection forEach; Then We are providing the 
anonymous class implementation of consumer interface in which we are defining our logic for the printing the number.
This code is very much verbose to give insight operation we are performing. Let's now write the same code using Java 8
Lambada expression.

**Method 2:**
```java
public class ModernMethod {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbers.forEach(value -> System.out.println(value));
    }
}
```
We converted our functional interface to lambada expression and which is less verbose but does the same operation 
over the data. Using lambada expression code is short and much readable. We can also do it by method reference 
like below:
```java
public class ModernMethod {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbers.forEach(System.out::println);
    }
}
```
We are going to learn method reference in detail in upcoming section.

* One major difference between implementing anonymous class and lambada expression is anonymous class create class files 
number of times it is defined. Just remember basic from java when we implement any anonymous class it will create 
class file like Classname$1.class and so on. So just consider if we iterate those number 20 times with implementation 
it will generate those many classes. While using lambada expression there is no extra classes is generated, it compiled 
within main class itself.

### Feature 2 - Writing your own functional interface
In section Feature - 0, We have seen what functional interfaces are, and then we used JDK Provided Functional Interface
In section Feature - 1 Lambada Expression. In This section we will create our own Functional Interface and Use it.

To Understand this we will take an example; In this example we will measure time took by a method for execution. Let's
create first in traditional way then we will convert it to functional programming way.

Let's first create a functional interface which have at most one method.
```java
@FunctionalInterface
public interface MeasurableMethod {
    void performOperation();
}
```
We have marked above interface with @FunctionalInterface optional annotation for functional interface. The annotation 
makes sure at compile time your interface do not have more than one abstract method.

Now Let's create a class which takes instance of MeasurableMethod Interface; execute operation and then reports 
execution time. 
```java
public class MeasureTime {

    public void measureTime(MeasurableMethod measurableMethod) {
        LocalTime startTime = LocalTime.now();
        measurableMethod.performOperation();
        LocalTime endTime = LocalTime.now();
        System.out.println("Time Took by Method is : "+
                Duration.between(startTime,endTime).toMillis());
    }
}
```

Now Let's create in traditional way we implement interface to class:
```java
class WaitForIt implements MeasurableMethod{

    @Override
    public void performOperation() {
        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
In above class we define everything we do below Java 8 versions. Let's execute it from a main method and then start 
writing same in functional programming way.
```java
public class TraditionalWay {
    public static void main(String[] args) {
        WaitForIt waitForIt = new WaitForIt();
        MeasureTime measureTime = new MeasureTime();
        measureTime.measureTime(waitForIt);

        measureTime.MeasureTime(new MeasurableMethod() {
            @Override
            public void performOperation() {
                try {
                    Thread.sleep(3*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
```
In above class we created the instance of WaitForIt which is implementation of the MeasurableMethod; or We created the 
anonymous implementation of the MeasurableMethod. Both of the ways are too verbose to implement simple functionality 
which can be defined in a couple of lines.

Let's write the functional equivalent of the above:
```java
public class FunctionalProgrammingWay {
    public static void main(String[] args) {
        MeasureTime measureTime = new MeasureTime();
        measureTime.measureTime(()-> {
            try {
                Thread.sleep(2*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        measureTime.measureTime(()-> {
            try {
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
```
In above, we have just passed our function as the lambada expression. If your performOperation method is more than a couple
of lines; It is recommended to implement in class and use it; because writing your implementation in lambada expression in
the class make it less readable.

There are a below interface which JDK already provides for most of simple operation.
1. Consumer
2. Supplier
3. Predicate
4. Function

Let's see each interface what it does.

**1. Consumer :-** Consumer interface takes the value and work with it. Consumer interface has one abstract method
which is accept; which takes the value and perform the operation on the value. The Consumer interface also
has default method which is andThen, Which takes another consumer as argument and return a consumer. andThen is for
chaining of consumers.

**2. Supplier :-** Supplier interface is for provide the value. This interface has only one method in it which is get.
There is no default method declared interface. It is used for lazy creation of object, and optional orElseGet or
elseThrow methods.

**3. Predicate :-** It is for evaluating expression and return response as true or false. It is most for stream filter
kind of operation, also used if we want to pass that to a function for dynamic conditions inside method (Strategy Pattern).
Predicate has abstract method test. Predicate has few default methods like negate() to invert the response, or & and
method to combine multiple predicates in a statement.

**4. Function :-** It takes an input and returns an output. This is for implement any function. This we can see use
in map of streams. The function has one abstract method apply. There are 2 default methods which are andThen and compose.
andThen chain function in sequence, while compose will reverse chain the function i.e. argument function will execute
first and then the caller function.

### Feature 3 - Method References
In last section we passed the lambada expression to method. In this section we will see to pass the method reference
instead of the lambda expression. If we write a function which is doing nothing calling other function, we can replace it
with the method reference. We can pass the method reference by 'Class or Object::method_name' or "target::method_name".

Static method reference is allow with class only; we can't use instance for static method reference.

Method reference routed in two ways; in one way parameter is passed as argument and in other way parameter is used as 
target to call the method. Let's see both by an example. 

First we have created a class Person which contains an instance method to print information about the person; 
Which uses the instance variable and print information. And This class also contains the static method which takes 
the person object as argument and prints the information.
```java
public class Person {
    private String name;
    private int age;

    public Person(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    public void printPersonInformation(){
        System.out.println(this);
    }

    public static void printPerson(Person person){
        System.out.println(person);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```
In the above class printPerson is static method; and printPersonInformation is the instance method.

Let's use the static method as method reference for our lambada expression:
```java
public class MethodReferenceAsArgument {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Test", 1),
                new Person("Test1", 2)
        );

        persons.forEach(Person::printPerson);
    }
}
```
We are calling static method of Person class which is printPerson(Person person), this takes person object as argument,
so in our stream foreach the parameter is used as argument and passed it to printPerson.

Let's use the instance method as method reference for our lambada expression:
```java
public class MethodReferenceAsTarget {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Test", 1),
                new Person("Test1", 2)
        );

        persons.forEach(Person::printPersonInformation);
    }
}
```
we are calling instance method of Person class printPersonInformation() which does not take any argument but prints 
instance information from whichever instance it is called. So in this the parameter is used as target on which the 
method is invoked.

### Feature 4 - Default method in Interfaces
Java version below 8 interfaces can only contain abstract methods; which intention is to define a contract for 
implementer class that the abstract method functions should be implemented. There was no concept of defining how those
function should be implemented or any default behaviour if not implemented.

Post Java 8, we can define method implementation in interfaces. To define a method implementation we need use keyword 
default for the method we are providing the implementation.

But why we want to write implementation in Interface; lets answer this question first. For understand why, consider
an example where you are writing a library, and you publish it. After passing a good period of time this library is start
using in multiple projects. This library contains an interface which contains the 4 abstract methods which are 
implemented by the projects who use it.

Let's consider you are improving your library and provide more functionality in it. For which you need to add more 
methods to interface; so client can write their own implementation for their part on this. But projects are already 
using your library; and it was compiling good and running. Once they upgrade your library version it stop compiling 
project because of the new methods defined in the interface that need to be implemented in client projects. For which
the client projects need to put development efforts, recompile code and redeploy.

To remediate this kind of situation default method introduced in Interface, so that client can update library version,
without having compilation error and can use existing methods in Interface. Also in other words, If a client is not 
using that particular method there is no impact on client, If they want to use this new introduced method, developers 
of library provided default behaviour what that function does. But in case if client wants to have their own custom 
behaviour of that particular method then they can override the default method, and they can provide their own 
implementation.

Let's understand few things about interface default methods.
1. Default methods can be overridden in subclasses.
```java
interface HelloTest {
    default void printHello(){
        System.out.println("Hello");
    }
}

public class Point1 implements HelloTest {
    @Override
    public void printHello() {
        System.out.println("Hello From Class");
    }

    public static void main(String[] args) {
        Point1 point = new Point1();
        point.printHello();
    }
}
```
2. SubTypes automatically carry over the default methods from their supertypes.
```java
public class Point2 implements HelloTest {

    public static void main(String[] args) {
        Point2 point = new Point2();
        point.printHello();
    }
}
```
3. For the interface that contribute a default method, the implementation in a subtype takes precedence over the one
   in supertypes.
4. Implementation in classes, include abstract declaration take precedence over all interface defaults.
```java
interface PrintHello{
    default void print(){
        System.out.println("Hello");
    }
}

class PrintWorld {
    public void print(){
        System.out.println("World");
    }
}

public class Point3And4 extends PrintWorld implements PrintHello {
    public static void main(String[] args) {
        Point3And4 point3And4 = new Point3And4();
        point3And4.print();
    }
}
```
5. If there's a conflict between two or more default method implementations, or there's a default abstract conflict
   between two interfaces, the inheriting class should disambiguate. i.e. If two interfaces have same method, and they
   don't have any class hierarchy then we need to resolve this problem in implementing class. Either we need to write our
   own implementation or can call one or both of super implementation in a sequence. In simple words I can say whenever
   diamond problem comes from interfaces; it should be solved by implementing class. If we have diamond problem we get 
   below error for below java code:
```java
interface Expandable {
    default void expand(){
        System.out.println("Expanding because of Expandable property");
    }
}
interface Gas{
    default void expand(){
        System.out.println("Expanding because of Gas State Property");
    }
}
public class Point5 implements Expandable,Gas {
    public static void main(String[] args) {
        Point5 point = new Point5();
        point.expand();
    }
}
```
```text
java: class com.codewithnaman.java8.feature4.Point5 inherits unrelated defaults for expand() from types com.codewithnaman.java8.feature4.Expandable and com.codewithnaman.java8.feature4.Gas
```
To resolve it we need to override the method expand() in the Point5 class. In case if we want to call one of interface 
method then we need to use the syntax <ClassOrInterfaceName>.super.<MethodName>, Because if we call 
<ClassOrInterfaceName>.<MethodName> then it may call the static method of class which may present there, 
so to avoid confusion we use the super reference. Let's fix Point5 class and call Gas interface method.
```java
public class Point5 implements Expandable,Gas {

    @Override
    public void expand() {
        Gas.super.expand();
    }

    public static void main(String[] args) {
        Point5 point = new Point5();
        point.expand();
    }
}
```

#### Interface vs Abstract Classes
* Abstract class can have state while an interface can't have state.
* We can inherit one abstract class but can implement any number of interfaces.

### Feature 5 - Final and effectively final variable
To understand the effectively final. Let's first take an example of inner class where we will use a variable from the 
outer scope in inner class. Let's see below class example:
```java
interface Multiplier{
    int multiplier(int value);
}

public class NumberMultiplier {
    public static void main(String[] args) {
        NumberMultiplier numberMultiplier = new NumberMultiplier();
        Multiplier multiplier = numberMultiplier.getMultiplier(5);
        System.out.println(multiplier.multiplier(3));
    }

    private Multiplier getMultiplier(int multiplyBy) {
        return new Multiplier() {
            @Override
            public int multiplier(int value) {
                return value*multiplyBy;
            }
        };
    }
}
```
In above example we have created anonymous inner class of interface in method getMultiplier. In the method we are 
creating the Multiplier instance and multiply by argument which we took in a function variable stored in stack. Now if 
I see we used the variable inside inner class, while the stack will be removed after the call. If till Java 7 We compile
below code then we will get below error:
```text
NumberMultiplier.java:17: error: local variable multiplyBy is accessed from within inner class; needs to be declared final
                return value*multiplyBy;
```
To fix the above error till Java 7 We need to write function like below:
```java
private Multiplier getMultiplier(final int multiplyBy) {
        return new Multiplier() {
            @Override
            public int multiplier(int value) {
                return value*multiplyBy;
            }
        };
    }
```
We need to add final access modifier to make it compile and run. Let's understand what happens when we declared the 
variable as final. When we declared the variable as final, then compiler adds that variable as instance variable to 
our anonymous class. It also creates a constructor for this variable and initialize it with the value.

Why didn't same thing happen when we did not declare the variable final? The reason for that is if variable is not
declare the final it's value may get changed on stack, and it will not reflect to instance variable of our anonymous
class and this may lead to bug. So, Java made it mandatory if we are using a variable inside the anonymous function
of lambada expression; it should be final or effectively final.

* Effectively final is in Java 8 which says the code will still work if we remove final; but it is understanding
  between compiler and developer that if variable is used in lambada or inner class then the variable will not
  going to modify. If you try to modify it then compiler will complain to you.

So you can write above code without using final keyword, and it will compile and run fine with Java 8.
```java
interface Multiplier{
    int multiplier(int value);
}

public class NumberMultiplier {
    public static void main(String[] args) {
        NumberMultiplier numberMultiplier = new NumberMultiplier();
        Multiplier multiplier = numberMultiplier.getMultiplier(5);
        System.out.println(multiplier.multiplier(3));
    }

    private Multiplier getMultiplier(int multiplyBy) {
        return value -> value*multiplyBy;
    }
}
```

### Feature 6 - Optional
In Java below 8, there were no way to convey the intention of method that can return a value as well as no value. So, 
instead of returning no value we return null. But it brings another chaos at user of our code, that user need to check
the null value his side, or he may get NullPointerException at runtime

To avoid above problem, Java 8 have introduced new Data Type which is called Optional<T>, where T shows generics of value
can be returned from method. Let's understand this by example, Let's first write a code in Java 7, and then we will use
Java 8 and refactor the code using Optional.
```java
public class TraditionalCode {

    public static void main(String[] args) {
        TraditionalCode instance = new TraditionalCode();
        Float divisionResult = instance.divide(3,2);
        System.out.println(divisionResult);
        System.out.println(divisionResult.compareTo(Float.valueOf(1.5f)));
        divisionResult = instance.divide(2,0);
        System.out.println(divisionResult);
        System.out.println(divisionResult.compareTo(Float.valueOf(1.5f)));
    }

    private Float divide(int number,int divisor){
        if(divisor==0)
            return null;
        return (float)number/divisor;
    }
}
```
In above code we are not using functional and wrote code as we write below Java 8. In above program the first division
works without any error or exception. But on second one we will get NullPointerException; since divisor is 0 and
function returns null on that. Let's protect our code from the NullPointerException.
```java
public class TraditionalCode {

    public static void main(String[] args) {
        TraditionalCode instance = new TraditionalCode();
        Float divisionResult = instance.divide(3, 2);
        System.out.println(divisionResult);
        System.out.println(divisionResult.compareTo(Float.valueOf(1.5f)));
        divisionResult = instance.divide(2, 0);
        System.out.println(divisionResult);
        if (Objects.nonNull(divisionResult))
            System.out.println(divisionResult.compareTo(Float.valueOf(1.5f)));
    }
}
```
This will fix the code but not very intuitive way to express from function side and client can expect null for every 
method and need to guard against each method. 

Let's write the same code with Java 8 Optional.
```java
public class OptionalUsedCode {

    public static void main(String[] args) {
        OptionalUsedCode instance = new OptionalUsedCode();
        Consumer<Float> compareResult = e-> System.out.println(e.compareTo(1.5f)==0);
        Optional<Float> divisionResult = instance.divide(3,2);
        divisionResult.ifPresent(System.out::println);
        divisionResult.ifPresent(compareResult);
        divisionResult = instance.divide(2,0);
        divisionResult.ifPresent(System.out::println);
        divisionResult.ifPresent(compareResult);
        divisionResult = instance.divide(5,2);
        if(divisionResult.isPresent()){
            System.out.println(divisionResult.get());
        }
    }

    private Optional<Float> divide(int number, int divisor){
        if(divisor==0)
            return Optional.empty();
        return Optional.of((float)number/divisor);
    }
}
```
In above code we can see that divide method clear its intention that it can return null by declaring the type Optional.
If we are sure that method will definitely return value then no need to use Optional. But, where we expect that we might
return value null value we should use Optional. 

To return the value we use factory method of Optional, and then return value using Optional.of(value). If we want to 
return no value then we will use Optional.empty() as return. 

The Client code then can use Optional.ifPresent(Consumer<T> consumer) method to execute code if value present. If we
have more lines of code to execute on any condition then use optional.isPresent() method in if block for value checking
and write code according to that. Calling get method on Optional without checking optional.isPresent() is bad practice.

To create the Optional value we can also use Optional.ofNullable(T value) method, if we pass the value it return the 
Optional with value otherwise Empty Optional.

### Feature 7 - Streams
In Java 8 we get completely new feature called streams. This is main construct to put the functional programming in Java
over declarative programming, we have learned both programming style in starting of the text. Let's look it from 
different perspective.

Let's take an example for this in which we have to sort list of persons on different criteria; We are going to use this 
example for further text.
```java
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
}
```
Above class we are going to use further for this section feature explanation.

Let's get started with few traditional method to sort the List of persons on the basis of age in increasing order,
in decreasing order and if age are same then sort on name.
```java
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
```
In above class we are sorting using method Collections.sort(list, comparator). We can highlight two points in above 
program which are going to improve the using functional programming style. 
1. Original persons list are modifying when we are calling sort method.
2. We have to write a lot of code, or I can say a lot of ceremony to just sort the list forward and backward. Also
can be optimized for age then name sort.

Let's write same program with Functional Programming Style.
```java
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
```
In above method we can see both the advantage.
1. Original Collection is not modified.
2. We have created Comparator using lambada expression; then we used default method of the Comparator class to create
reverse comparator of forward comparator and then use thenComparing method to add another attribute for which we want
to compare if age of two persons are same. We have Also used Comparator.comparingInt default method of Comparator class
which takes method reference as argument and return comparator for comparing the integers by calling those methods.

In the last section we have seen option available as part of stream sorting. Let's go through one of important Java 
streams property which is laziness. Let's understand more about that below:
1. Streams are lazy by default in nature i.e. if we define any stream and didn't perform the any terminal operation then
stream operations are not evaluated.
2. As soon as streams are able to satisfy the terminal operation result, it will stop evaluating further data on stream.
For example: As soon as findFirst() function find first matching data element; streams stop evaluating operation or in
other words post finding first element it stops processing other elements in list.

Let's see the example for 1st and 2nd point below:
```java
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
```
If we execute the above code then we will not see any output line with "Filtering age 30", because since stream does
not contain any terminal operation; so, it didn't get evaluated.

On second stream we see 3 lines of "Filtering age 28" after which it gets the first result, and it terminates further 
execution of the stream and perform the operation on element get from findFirst() method.

Now how we identify the terminal operation of stream and intermediate operation of stream. The thumb rule for this is 
that if a function is returning Stream, or it's subtype then it is intermediate operation. If it is returning something 
else then it is terminal operation.

#### Built-in Functional interfaces provided by JDK
1. Consumer\<U> : Take 1 parameter and returns nothing; has accept method which performs the operation.
  foreach method takes consumer. 
2. Supplier\<U>  : Take 0 parameter and return 1 parameter; has get method which returns the value.
  orElse method takes Supplier. 
3. Function\<U,R> : Takes 1 parameter and return 1 parameter; has apply method which apply the opertaion and
  return the result. map method takes Function. 
4. Predicate\<T> : Takes 1 parameter and return boolean result; has test method to perform the operation.
  filter method takes Predicate. 
5. BiFunction\<T, U, R> : Takes 2 parameters and return 1 parameter; has apply method which performs the operation
  and return the result. reduce method takes BiFunction. 
6. BiConsumer\<T, U> : Takes 2 parameters and returns nothing; has accept method which perform the operation.

#### Few useful methods of Built-in Functional Interfaces
1. negate : This is default method in Predicate class. This is used where we want to perform reverse operation of a
  particular predicate. The most preferred way to do this provides ! against the predicate in lambada function. Then
  We can prefer this negate method. Example for same is below:
```java
public class UsefulFunctionalInterfaceMethods {

    public static void main(String[] args) {
        Predicate<Integer> divisibleByTwo = number -> number%2==0;
        System.out.println(divisibleByTwo.test(5));
        System.out.println(divisibleByTwo.negate().test(5));
    }
}
```
2. Composing Predicates: Composing predicates means combining the predicates with AND,OR or NOT operations. Just consider
   you have two predicates, and you want to perform the filtering based on the both of the condition. So, combining 
   two predicate and perform filtering on them, we use and(&&), or(||) and negate(!) function. Example for the same
   below:
```java
public class UsefulFunctionalInterfaceMethods {

    public static void main(String[] args) {
        Predicate<Integer> divisibleByTwo = number -> number%2==0;
        Predicate<Integer> divisibleByFive = number -> number%5==0;
        System.out.println(divisibleByFive.or(divisibleByTwo).test(4));
        System.out.println(divisibleByFive.or(divisibleByTwo).test(7));
        System.out.println(divisibleByFive.and(divisibleByTwo).test(10));
        System.out.println(divisibleByFive.and(divisibleByTwo).test(8));
    }
}
```

3. Composing Functions: Similar like predicates the Function interface provides the default method to compose the function
  which is andThen and compose method.
```java
import java.util.function.Function;
import java.util.function.Predicate;

public class UsefulFunctionalInterfaceMethods {

    public static void main(String[] args) {
        Function<Integer,Integer> incrementByOne = e -> e+1;
        Function<Integer,Integer> doubleTheNumber =e -> e*2;
        System.out.println(incrementByOne.andThen(doubleTheNumber).apply(2)); //6
        System.out.println(incrementByOne.compose(doubleTheNumber).apply(2)); //5
    }
}
```
Let's see the difference between andThen and compose function. In andThen method argument function execute after the 
instance function on which andThen method is called. In our example given number is first increment by 1 and then get 
doubled; That's why we are getting output as 6. The number 2 is first increment by 1 which becomes 3, and then it 
is doubled which made our output 6.

While in compose method argument function execute first after that instance function on which compose method is called
executes. In our example given number is first doubled and then increment by 1 that's why we are getting output 5. The
number 2 is first doubled and becomes 4, and then it increments by 1 which made our output 5.

#### Few Useful Stream methods
1. map: map method convert stream of one type to another like in below example we are converting Person instances stream
to String stream.
```java
import java.util.List;

public class UsefulStreamMethods {

    public static void main(String[] args) {
        List<Person> persons = PersonsListBuilder.getPersonsList();
        persons.stream().map(Person::getName).forEach(System.out::println);
    }
}
```

2. mapToInt: mapToInt method converts our stream to primitive type stream. In Java, we treat primitive datatype streams
differently for which we have classes IntStream,LongStream and DoubleStream. These classes provide some additional 
method like min,max and SummaryStatistics like mathematical functions on these streams. Let's see below example:
```java
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class UsefulStreamMethods {

    public static void main(String[] args) {
        List<Person> persons = PersonsListBuilder.getPersonsList();
        IntStream personsAgeStream = persons.stream().mapToInt(Person::getAge);
        OptionalInt minimumAge = personsAgeStream.min();
        minimumAge.ifPresent(System.out::println); //20
        personsAgeStream = persons.stream().mapToInt(Person::getAge);
        OptionalInt maximumAge = personsAgeStream.max();
        maximumAge.ifPresent(System.out::println); //38
        personsAgeStream = persons.stream().mapToInt(Person::getAge);
        IntSummaryStatistics summaryStatistics = personsAgeStream.summaryStatistics();
        System.out.println(summaryStatistics); //IntSummaryStatistics{count=13, sum=371, min=20, average=28.538462, max=38}
    }
}
```
On second line we are getting the IntStream which is primitive stream.
 * min: On third line of program we called min function available as part of IntStream; Which returns min value as
   OptionalInt(primitive int Optional) and we can use Optional Class method on it to get value or perform operation over
   the output. This returns Optional because if stream is empty then it returns Option.empty().
 * max: On sixth lime of program we called max function available as part of IntStream; Which returns max value as
   OptionalInt(primitive int Optional) and we can use Optional Class method on it to get value or perform operation over
   the output. This returns Optional because if stream is empty then it returns Option.empty().
 * SummaryStatistics: On ninth line we are calling summaryStatistics method which returns IntSummaryStatistics,
   DoubleSummaryStatistics and LongSummaryStatistics depending on stream. It gives us information about elements
   calculated basic operation; like count,sum,min,max and average.

**Notice on Line 2 we initialize the stream variable and then on Line 5 we re-initializes with same value. Now questions
comes why we didn't use same variable again without re-initializing. To answer to that question, Let's highlight one more
characteristics of stream that Stream is immutable and also once stream is operated it is closed for further operation to 
be performed i.e. we have performed one terminal operation on a stream then another terminal operation can not be 
performed on same stream. If we do that; we will get Exception "java.lang.IllegalStateException: stream has already 
been operated upon or closed"**

3. filter: filter function takes a predicate and test each element against this predicate; when predicate returns true
then element will be further processed if predicate returns false then the element is dropped from the stream for 
further processing.
```java
public class UsefulStreamMethods {

    public static void main(String[] args) {
        List<Person> persons = PersonsListBuilder.getPersonsList();
        System.out.println(persons.stream().filter(e -> e.getAge() > 30).count()); //3
    }
}
```
In above example we are filtering persons whose age is greater than 30, and we get count of it. So this function will 
drop all the elements whose age is less than or equal to 30. 

4. allMatch/anyMatch: These function also takes predicate as argument and returns boolean i.e. it is a terminal function.
allMatch returns true if all elements of stream is qualify the predicate condition otherwise it returns false. anyMatch 
returns true if any of element in stream qualify the predicate condition.
```java
public class UsefulStreamMethods {

    public static void main(String[] args) {
        List<Person> persons = PersonsListBuilder.getPersonsList();
        System.out.println(persons.stream().allMatch(e->e.getAge()<50)); // true
        System.out.println(persons.stream().anyMatch(e->e.getAge()>50)); // false
    }
}
```

5. flatMap: flatMap produces stream out of element. Let's take an example to understand this.
```java
public class UsefulStreamMethods {

    public static void main(String[] args) {
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

        synonyms.values().stream().forEach(System.out::println); // [Enrage, Infuriate, Nettle] [Reply, Respond, Retort]
        synonyms.values().stream().flatMap(e-> e.stream()).forEach(System.out::println); // Enrage Infuriate Nettle Reply Respond Retort
    }
}
```
We have created a list of synonyms and stored them as map. Let's say I want to print List of all synonym word. When
I just converted the values in stream I get the list which I need to process further in foreach to get each string. 
Here flatMap helps me to convert list of String to Stream of String.

6. concat : This is used to concat two different streams to one.
```java
public class UsefulStreamMethods {

    public static void main(String[] args) {
        System.out.println("======Concat======");
        List<String> stream1 = new ArrayList<>();
        stream1.add("Apple");
        stream1.add("Mango");
        List<String> stream2 = new ArrayList<>();
        stream2.add("BMW");
        stream2.add("Mercedes");
        Stream.concat(stream1.stream(),stream2.stream()).forEach(System.out::println);
    }
}
```

7. zip : zip will put the elements alternatively from different streams. For example :

Stream 1 : 1  2 3 4 5

Stream 2 : a b c d e

Then zip will return like (1,a),(2,b).(3,c),(4,d),(5,e). This is important part for functional programming but zip
operation is not directly available in the java while it is in Scala. So we can perform same operation in Java
like below:
```java
public class UsefulStreamMethods {

    public static void main(String[] args) {
        System.out.println("=====Zip=====");
        List<Integer> list1 = Arrays.asList(1,2,3,4,5);
        List<String> list2 = Arrays.asList("a","b","c","d","e");
        IntStream.range(0,Math.min(list1.size(),list2.size()))
                .mapToObj(i-> new String[] {list1.get(i).toString(),list2.get(i)})
                .forEach(element -> System.out.println(element[0]+","+element[1]));
    }
}
``` 

#### Collecting Stream data post-processing
Till now, we have performed operation on stream and print out or store in a variable. What If we want to process 
elements and want to store in any collection like List,Set or Map. Streams provide collect method to store elements
on a collection and store it.

Let's see different methods and techniques to store the data in different form.

1. Let's take example of List we have sorted by age of person in previous section and print it. Let's consider after
sorting we need to store this new List, so we can use it further. For collecting into the List we use function collect
which takes and Collector; We get most of common Collector in a utility class Collectors. Let's see how we can store
sorted list below:
```java
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DataCollectionPostProcessingStream {
    public static void main(String[] args) {
        List<Person> persons = PersonsListBuilder.getPersonsList();
        List<Person> sortedByAgePersonsList = persons.stream()
                .sorted(Comparator.comparingInt(Person::getAge)).collect(Collectors.toList());
        sortedByAgePersonsList.forEach(System.out::println);
    }
}
```

2. Just consider we want Set in that case we can use Collectors.toSet() in collect method. 
```java
public class DataCollectionPostProcessingStream {
    public static void main(String[] args) {
        List<Person> persons = PersonsListBuilder.getPersonsList();
        Set<Person> sortedByAgePersonsSet = persons.stream()
                .filter(e-> e.getAge()>25).collect(Collectors.toSet());
        System.out.println(sortedByAgePersonsSet.size());
    }
}
```

3. Now let's collect data in map. For this example consider we want to make map of all the persons with same age. In 
this case Integer will be our key which represents age and List<Person> will be our value. Let's see in practice.
```java
public class DataCollectionPostProcessingStream {
    public static void main(String[] args) {
        List<Person> persons = PersonsListBuilder.getPersonsList();
        Map<Integer,List<Person>> personsByTheirAge = persons.stream().collect(Collectors.groupingBy(Person::getAge));
        //{32=[Person{name='Marshall', age=32}, Person{name='Victoria', age=32}], 20=[Person{name='Annabelle', age=20}],
        // 38=[Person{name='Barney', age=38}], 25=[Person{name='Monica', age=25}, Person{name='Joey', age=25}, Person{name='Tracy', age=25}],
        // 28=[Person{name='Ted', age=28}, Person{name='Chandler', age=28}, Person{name='Lily', age=28}], 
        // 30=[Person{name='Rachel', age=30}, Person{name='Ross', age=30}, Person{name='Robin', age=30}]}
        System.out.println(personsByTheirAge);
    }
}
```
While collecting stream as map, we need to group by the objects on some key basis, here the key is age of person. In 
above code snippet collect method take Collectors.groupingBy which further takes a classifier as input which is key for 
the map and in above case the value is List of the object at terminal of stream.

Let's say we want to create Map by their first character in map.
```java
public class DataCollectionPostProcessingStream {
    public static void main(String[] args) {
        List<Person> persons = PersonsListBuilder.getPersonsList();
        Map<Character,List<Person>> personsByTheirFirstLetterOfName = persons.stream()
                .collect(Collectors.groupingBy(e->e.getName().charAt(0)));
        //{A=[Person{name='Annabelle', age=20}], B=[Person{name='Barney', age=38}], 
        // R=[Person{name='Rachel', age=30}, Person{name='Ross', age=30}, Person{name='Robin', age=30}], 
        // C=[Person{name='Chandler', age=28}], T=[Person{name='Ted', age=28}, Person{name='Tracy', age=25}], 
        // V=[Person{name='Victoria', age=32}], J=[Person{name='Joey', age=25}], L=[Person{name='Lily', age=28}], 
        // M=[Person{name='Monica', age=25}, Person{name='Marshall', age=32}]}
        System.out.println(personsByTheirFirstLetterOfName);
    }
}
```
In above code snippet we defined custom key which is person first character. The classifier is a function takes
a function which takes an input and return an output.

Now Let's consider we do need complete object as list of value; we just want to list of age of person; Then we will 
a new function as part of collect which is mapping function, that will change person object to Integer and collect
in a List. Let's see that in practice.
```java
public class DataCollectionPostProcessingStream {
    public static void main(String[] args) {
        List<Person> persons = PersonsListBuilder.getPersonsList();
        Map<Character, List<Integer>> charAge =
                persons
                        .stream()
                        .collect(Collectors.groupingBy(person -> person.getName().charAt(0),
                                Collectors.mapping(person-> person.getAge(),Collectors.toList())));
        //{A=[20], B=[38], R=[30, 30, 30], C=[28], T=[28, 25], V=[32], J=[25], L=[28], M=[25, 32]}
        System.out.println(charAge);
    }
}
```
In above code snippet our key is person's name first character and value is person's age. For get desired
type of value we use static mapping function of Collectors class. mapping takes a function and return
the value which we want to collect, the second argument takes how we want to collect values like in list, set or any
other collection.

As of now we have a collection on value part of map, but what if we want just value instead of collection. Let's see
that in practice.
```java
public class DataCollectionPostProcessingStream {
    public static void main(String[] args) {
        List<Person> persons = PersonsListBuilder.getPersonsList();
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
    }
}
```
In above code snippet we are grouping by the first character of person name, then we wanna eldest in the group
so what we did we are performing maxBy operation which takes comparator on which the comparison will be performed
and max value will be return. It might be the collection can empty, so it is returning the Optional of the person.

Let's understand a little more complex operation we have for groupingBy function. For this example consider we want 
Person with Maximum age and if the age of person is same then name length will take in consideration and whose name 
length is large that person is considered.
```java
public class DataCollectionPostProcessingStream {
    public static void main(String[] args) {
        List<Person> persons = PersonsListBuilder.getPersonsList();
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
```
Collectors.reducing takes BinaryOperator which performs some operation and return the same type.

#### New Map Interface functions
With Java 8, we get few new functions that make our life easier. Let's see each function one by one.

1. compute : This is default method of Map class. We use this method to perform some operation on a map for a particular
  key. If key is not present then this method can throw a NullPointException.
```java
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
    }
}
```

2. merge : In above method we can see that when we perform the compute function on "Banana" then it throws NullPointer
Exception; We can resolve this by using below:
```java
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
    }
}
```
But, the above code is not much intuitive. We will use the merge function to make it better. Let's understand how merge
method makes it better.

If we see compute method signature it takes a key and BiFunction which has two arguments key and value. merge method 
takes 3 argument in which first is the key to perform the operation, second is default value if the key is not present
then this is the value which will be used and third is BiFunction same like compute to perform the operation if key is
present then it takes current value and perform the operation. But point to note here is that BiFunction takes the 
argument value and default value; we don't get key.
```java
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
    }
}
```

So, if the key is not present in merge then it will add the key with default value; if the key is present then it will
increment the value by defaultValue as provided in lambada expression for the BiFunction. Be cautious about this method
because when BiFunction returns null then merge removes the key.

3. computeIfPresent and computeIfAbsent : These are again default methods of the Map class. This is used when we want 
to perform some operation on the map if and only if the key is present or if and only if the is absent.
computeIfPresent It also avoids NPE if the key is not present.
```java
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
```

### Java 8 Lambda Expression Best Practices
* Prefer method references over lambada expression
* Use lambda function as glue code; Keep them short and crispy.
* Avoid lambda function bigger than 2 lines.
* Use built-in interfaces for 0,1 or 2 parameters; as compare to create your owns.
* If you want more than 3 parameters or more in input to lambda you can pass the Object to built-in interfaces' lambda;
  or you can create your own interfaces.
* Try to give proper names to lambda variable which will help in readable code.
* Avoid shared mutability in Lambda functions.
* When we are using lambada function we should try to use the [pure function](http://blog.agiledeveloper.com/2015/12/two-rules-for-purity.html).

