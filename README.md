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
In Previous versions of Java below 8; To create a concrete implementation we implement interface in classes or create 
anonymous class and pass it to function or assign to variable. In Java 8 We get Lambada Expression; Where we can define
or implement an interface without creating anonymous classes and provide implementation in same line where you are 
declaring variable. 

A functional interface is useful to create and assign a lambada expression. To create a functional interface the 
interface should qualify below properties:
* It should contain only one abstract method
* Interface can have default methods
* Interface can have static methods
* Optionally we can mark the @FunctionalInterface annotation

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
We ar going to learn method reference in detail in upcoming section.

* One major difference between implementing anonymous class and lambada expression is anonymous class create class files 
number of times it is defined. Just remember basic from java when we implement any anonymous class it will create 
class file like Classname$1.class and so on. So just consider if we iterate those number 20 times with implementation 
it will generate those many classes. While using lambada expression there is no extra classes is generated, it compiled 
within main class itself.

### Feature 2 - Writing your own functional interface
In section Feature - 0, We have seen what functional interfaces are, and then we used JDK Provided Functional Interface
In section Feature - 1 Lambada Expression. In This section we will create our own Functional Interface and Use it.

To Understand this we will take an example; In this example we will measure time took by a method for execution. Let's
create first write in traditional way then we will convert it to functional programming way.

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

    public void MeasureTime(MeasurableMethod measurableMethod) {
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
        measureTime.MeasureTime(waitForIt);

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
There is no default method declared interface. It is used for lazy creation of object, and optional's orElseGet or
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

But why we want to write implementation in Interface; Let's answer this question first. For understand why Let's consider
an example where you are writing a library, and you publish it. After passing a good period of time this library is start
using in multiple projects. This library contains an interface which contains the 4 abstract methods which are 
implemented by the projects who use it.

Let's consider you are improving your library for which you need to add more method to interface; so client can write 
their own implementation for this. But projects are already using your library; and it was compiling good and running 
until you have introduced this interface methods. 

And here is chaos is created for all projects which implemented library interface need to implement this
particular method and recompile it and deploy it. Which solutions come with the default method in interface. To
provide implementation of a method in an interface, we need to use default keyword for method and can provide the
implementation. By providing default information you are providing the default behaviour of method; If a client doesn't
want the default behaviour then he can override and kick in his project specific behaviour. But the default methods
in interface will gives you flexibility to enhance the library without breaking client applications.

Let's understand few things about interface default methods.
1. Default methods can be overridden in subclasses.
2. SubTypes automatically carry over the default methods from their supertypes.
3. For the interface that contribute a default method, the implementation in a subtype takes precedence over the one
   in supertypes.
4. Implementation in classes, include abstract declaration take precedence over all interface defaults.
5. If there's a conflict between two or more default method implementations, or there's a default abstract conflict
   between two interfaces, the inheriting class should disambiguate. i.e. If two interfaces have same method, and they
   don't have any class hierarchy then we need to resolve this problem in implementing class. Either we need to write our
   own implementation or can call one or both of super implementation in a sequence. In simple words I can say whenever
   diamond problem comes from interfaces; it should be solved by implementing class. If we have diamond problem we get below
   error:
```text
Error:(3, 8) java: types java8.feature4.FastFly and java8.feature4.Sail are incompatible;
  class java8.feature4.SeaPlane inherits unrelated defaults for cruise() from types java8.feature4.FastFly and java8.feature4.Sail
``` 

When we want to class interface method we will use <ClassName>.super.<MethodName>, Because if we call
<ClassName>.<MethodName> then it may call the static method of class which may present there, so to avoid confusion
we use the super reference.

#### Interface vs Abstract Classes
* Abstract class can have state while an interface can't have state.
* We can inherit one abstract class but can implement any number of interfaces.

## Feature 5 : Final, inner classes and effectively final
Let's understand code [TraditionalInnerClass](src/main/java/java8/feature5/TraditionalInnerClass.java). In the code
we are declaring an interface which have one abstract method which take a value and multiply with some value and
return the result.
```java
 public static void main(String[] args) {
        Multiplier  multiplier = createMultiplier(4);
        System.out.println(multiplier.multiplier(3));
    }

    private static Multiplier createMultiplier(int multiplier) {
        final int instMultiplier = multiplier;
        return new Multiplier() {
            @Override
            public int multiplier(int value) {
                return value * instMultiplier;
            }
        };
    }
```

Now we have created anonymous inner class of interface in method createMultiplier. In the method we are creating the
Multiplier instance and multiply by argument multiplier which we took in a function variable stored in stack. Now
if I see we used the variable inside inner class, while the stack will be removed after the call, also if you don't
declare the variable as final till Java 7 then code will not compile and give error. So what happens internally after
declaring variable final, that code will compile. Let's find out th answer.

When we declare the variable as final then compiler adds this variable to anonymous class as instance variable and
create a consutructor for this variable and initialize it with the value. If we don't declare the value as final
then chances that value is going to be changed on stack and if will not reflect to instance variable which is created
by the compiler; which can leads to bug. So, Java made it mandatory if you are using a variable inside the anonymous
function or lambada expression; it should be final or effectively final.

* Effectively final is in Java 8 which says the code will still work if we remove final; but it is understanding
  between compiler and developer that if variable is used in lambada or inner class then the variable will not
  going to modify. If you try to modify it then compiler will complain to you.


## More on java streams, classes and operations
## Sorting example
Let's take an example of sorting in Java 7 then we will it to Java 8 and then we understnad the prespective from the
functional point of view.

[TraditionalMethod](src/main/java/java8/example/sorting/TraditionalMethod.java) is example in which we are sorting
using Collections.sort(list, comparator). With the above implementation I can see two problems. One is that
we are mutating objects and second is that for sorting on age we have lot of ceremony and if want to reverse sort
we need to change the condition.

[ModernMethod1](src/main/java/java8/example/sorting/ModernMethod1.java) use the Java8 Lambada function and steams
to sort. In which we can see original collection is not modified and reverse is done using calling default method
of the comparator interface.

Also, we can use the comparator static method comparing which takes Function interface lambada, in which we need to
pass on which field we need to sort.
```text
 persons.stream().sorted(Comparator.comparing(Person::getAge)).forEach(System.out::println);
```

Sorting on multiple fields:
```text
      persons.
                stream().
                sorted(Comparator.
                        comparing(Person::getAge).
                        thenComparing(Person::getName))
                .forEach(System.out::println);
```

### Min and Max operation
Let's take our last example, and we want to find the eldest person in our list. Then we can use again comparing method
of the Comparator interface and pass the function to max method. If there are two persons of same age in our list it
will return the first person it finds in the list who ie eldest. It returns optional because if the list empty it
will return the Optional empty or we can use optional to return default value or throw an exception.


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

## Streams Laziness
Streams are lazy be default; i.e. whenever we perform the operation and if we just perform the intermediate operation
without any terminal operation they are not going to call. While when we call the terminal operation, it will compute
the operation depending on your terminal operation. Let's say if you write findFirst() as terminal operation,
then the stream will process till it find the first item, as it find the first item which satisfies all condition
it will process through steps which need to qualify. In example
[TestStreamLaziness](src/main/java/java8/example/stream/lazy/TestStreamLaziness.java) we can see that we
initialized the stream but no intermediate operation is called until we called the findFirst(). Also, we can
see that as the findfirst() get its result on 4 rest of the elements are not processed which is also
show efficiency of stream.

Now how we identify the terminal operation of stream of and intermediate operation of stream. The thumb rule for this
I can say is that if a function is returning Stream, or it's subtype then it is intermediate operation. If it is
returning something else then it is terminal operation.

## Java 8 lambada or stream functions
* negate : This is default method in Predicate class. This is used where we want to perform reverse operation of a
  particular predicate. The most preferred way to do this provides ! against the predicate in lambada function. Then
  We can prefer this negate method.

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

* Composing Predicates: Composing predicates means combining the predicates with AND,OR or NOT operations. Just consider
  you have two predicates and you want to perform the filtering based on the both of the condition; and you are passing
  the predicate to a different function. So to pass two predicates as one predicate to function you need to combine
  in some logical group. For which Predicate interface provide default methods to combine which is and,or and negate
  method.
```text
Predicate<Integer> isEven = e-> e%2==0;
Predicate<Integer> isGT100 = e-> e>100;
passToFunction(isEven.and(isGT100));
```
So in above example we can see we compose two aggreate in one and passing it to the function.

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
    * Consumer\<U> : Take 1 parameter and returns nothing; has accept method which performs the operation.
      foreach method takes consumer.
    * Supplier\<U>  : Take 0 parameter and return 1 parameter; has get method which returns the value.
      orElse method takes Supplier.
    * Function\<U,R> : Takes 1 parameter and return 1 parameter; has apply method which apply the opertaion and
      return the result. map method takes Function.
    * Predicate\<T> : Takes 1 parameter and return boolean result; has test method to perform the operation.
      filter method takes Predicate.
    * BiFunction\<T, U, R> : Takes 2 parameters and return 1 parameter; has apply method which performs the operation
      and return the result. reduce method takes BiFunction.
    * BiConsumer<T, U> : Takes 2 parameters and returns nothing; has accept method which perform the operation.
* If you want more than 3 parameters or more as input to lambda you can pass the Object to built-in interfaces lambda;
  or you can create your own interfaces.
* Try to give proper names to lambda variable which will help in readable code.
* Avoid shared mutability in Lambda functions.
* 

