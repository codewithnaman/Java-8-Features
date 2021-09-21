# Java 8 Features and Examples
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


## Reduce operation on list
Let's take an example [ListExample](src/main/java/java8/example/reduce/ListExample.java). Let's consider we have
a stream of values on which we perform some operation and then want to collect values in Collection, then we will use
different reduce methods for collecting in different collection types.

If we want to collect the streams in List or any other collection, First thing never ever do like below:
```text
        List<Person> personsSortedByAge = new ArrayList<>();
         persons
                .stream()
                .sorted(Comparator.comparing(Person::getAge))
                .forEach(person -> personsSortedByAge.add(person));
```

Use Collectors class from stream package and then use method to collect the data after transformation like below:
```text
         persons
                .stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList());
```

Similar List we can collect result in set by Collectors.toSet().

Let's now understand how to store the collection in the Map.
```text
  Map<Integer,List<Person>> ageGroupOfPerson =
                persons
                .stream()
                .collect(Collectors.groupingBy(Person::getAge));
```
For collecting stream as map, we need to group by the objects on some key basis. As we can see collect method take
Collectors.groupingBy which further takes a classifier as input which is key for the map and in above case the value is
List of the object at terminal of stream.

```text
        Map<Character,List<Person>> firstCharacterGroupOfPerson =
                persons
                        .stream()
                        .collect(Collectors.groupingBy(person -> person.getName().charAt(0)));
```

In above example we defined the custom key which is person first character. The classifier is a function takes
a function which takes an input and return an output.

By classifier we have seen how to decide the key, Let's see how we can specify type of value map can contain.
```text
Map<Character, List<Integer>> charAge =
                persons
                        .stream()
                        .collect(groupingBy(person -> person.getName().charAt(0),
                                mapping(person-> person.getAge(),toList())));
```
So in above example we can see our key is person's name first character and value is person's age. For get desired
type of value we use static function of Collectors class which name is mapping. mapping takes a function and return
the value which we want to collect, the second argument takes how we want to collect values like in list, set or any
other collection.


We have collected value in another collection, what if we want a single key value pair collection. Let's understand
this as well.
```text
        System.out.println("==========Char eldest collection===========");
        Function<Person, Character> firstCharacterOfPersonName = person -> person.getName().charAt(0);
        Comparator<Person> byAge = Comparator.comparing(Person::getAge);
        Map<Character, Optional<Person>> charEldest =
                persons
                        .stream()
                        .collect(groupingBy(firstCharacterOfPersonName, maxBy(byAge)));
        System.out.println(charEldest);
```

In above example as we can see we are grouping by the first character of person name, then we wanna eldest in the group
so what we did we are performing maxBy operation which takes comparator on which the comparison will be performed
and max value will be return. It might be the collection can empty, so it is returning the Optional of the person.

Now let's understand more complex operation in grouping by like reducing on the condition; like if the name length
of two persons is equal then eldest person will taken otherwise whose name length is greater than he taken.
```text
        System.out.println("==========Name length and then eldest collection===========");
        Function<Person, Integer> nameLength = person -> person.getName().length();
        BinaryOperator<Person> criteria = (person1, person2) -> {
            if (person1.getName().length() == person2.getName().length()) {
                return person1.getAge() > person2.getAge() ? person1 : person2;
            } else {
                return person1.getName().length() > person2.getName().length() ? person1 : person2;
            }
        };
        Map<Integer, Optional<Person>> nameLengthThenEldest =
                persons
                        .stream()
                        .collect(groupingBy(nameLength, reducing(criteria)));
        System.out.println(nameLengthThenEldest);
```

We can transform the resulting field as below:
```text
        System.out.println("==========Char with eldest person age collection===========");
        Map<Character, Optional<Integer>> charEldestAge =
                persons
                        .stream()
                        .collect(groupingBy(firstCharacterOfPersonName,
                                mapping(Person::getAge, maxBy(Integer::compare))));
        System.out.println(charEldestAge);
```



## Java 8 lambada or stream functions


* compute : This is default method of Map class. We use this method to perform some operation on a map for a particular
  key. For Example:
```text
map.compute("apple", (key, value) -> value + 1);
```
The above example is one of the common use case. But if your provided key is not present then this method throws NPE.
Let's resolve this problem by using one other method of the Map Class.

* merge : If we use compute method and want to avoid NPE, then we can do something like below with compute function.
```text
map.compute("apple", (key, value) -> Objects.nonNull(value) ? value + 1 : 1);
```
But, the above code is not much intuitive. We will use the merge function to it better. Let's understand first
how compute works and how merge is going to work for us.

If we see compute method signature it takes a key and BiFunction which has two arguments key and value. But the
merge takes 3 argument; first is key second is default value if key is not present third is the BiFunction which
tells how you want to use default value and existing value of the key. Let's see this by example:

```text
map.merge("apple",1,(value,defaultValue) -> value+defaultValue);
```

So, if the key is not present in merge then it will add the key with default value; if the key is present then it will
increment the value by defaultValue as provided in lambada expression for the BiFunction. Be cautious about this method
because when BiFunction returns null then merge removes the key.

* computeIfPresent : This is again a default method of the Map class. This is used when we want to perform some operation
  on the map if and only if the key is present. It also avoids NPE if the key is not present.

* computeIfAbsent : This is again a default method of the Map Class. This is just opposite of the computeIfPresent. If
  the key is not present then it performs the operation. Let's see example for both:
```text
map.computeIfAbsent("apple", key-> 0);
map.computeIfPresent("apple",(key,value) - > value+1);
```

* When we are using lambada function we should try to use the [pure function](http://blog.agiledeveloper.com/2015/12/two-rules-for-purity.html).

* summaryStatistics : This is to get the summary statistics on the double stream.
```java
System.out.println(
    Stream.of(1,2,3,4,5,6).mapToDouble(e->e).summaryStatistics()
);
```
or
```java
System.out.println(
    Stream.of(1,2,3,4,5,6).collect(Collectors.summarizingDouble(e->e))
);
```

Both return the DoubleSummaryStatistics which contains the number of element stream have, sum,avarage and min and max
value for the stream.

* concat : This is used to concat two different streams to one.
```java
Stream.concat(stream1,stream2)
```

* zip : zip will put the elements alternatively from different streams. For example :

Stream 1 : 1  2 3 4 5

Stream 2 : a b c d e

Then zip will return like (1,a),(2,b).(3,c),(4,d),(5,e). This is important part for functional programming but zip
operation is not directly available in the java while it is in Scala. So we can perform same operation in Java
like below:
```text
List<Integer> list1 = Arrays.asList(1,2,3,4,5);
List<String> list2 = Arrays.asList("a","b","c","d","e");
IntStream.range(0,Math.min(list1.size(),list.size())
         .mapToObj(i-> new String[] {list1.get(i).toString(),list2.get(i)})
         .forEach(element -> System.out.println(element[0]+","+element[1]));
``` 



* Composing Functions: Similar like predicates the Function interface provides the default method to compose the function
  which is andThen and compose method.
```text
Function<Integer,Integer> inc = e -> e+1;
Function<Integer,Integer> double = e -> e*2;
passToFunction(inc.andThen(double));
passToFunction(inc.compose(double));
```
Now what is difference between the both functions; The andThen passed function will be applied after the inc is done
its work while compose passed function will be applied before the inc is called. So for example we have number 5 and
we call above code then and then will print 12 i.e. first it incremented 5 with 1 which becomes 6 and then perform
double, so it became 12. While same in compose the output will be 11; because it will first double the number for which
it became 10 and then increment by 1, so it will become 11.

To combine the functions we need to take care of output of one function should be input the other function.

## Java 8 Lambda Expression Best Practices
* Prefer method references over lambada expression
* Use lambda function as glue code; Keep them short and crispy.
* Avoid lambda function bigger than 2 lines.
* Use built-in interfaces for 0,1 or 2 parameters; as compare to create your owns:
  
* If you want more than 3 parameters or more as input to lambda you can pass the Object to built-in interfaces lambda;
  or you can create your own interfaces.
* Try to give proper names to lambda variable which will help in readable code.
* Avoid shared mutability in Lambda functions.
* 

