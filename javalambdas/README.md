# Java Lambdas
This project will contain example of lambdas function feature from Java 8

## Chapter 2 Exercise
1.
```java
public interface Function<T,R>{
    R apply(T t)
}
```

* a. T ---> Function ---> R
* b. This function could be used for **absolute value function**
* c. `(x,y) -> x + 1`

2. `ThreadLocal.withInitial(()->new SimpleDateFormat("dd-MMM-yyyy"))`
3.
* a. Yes
* b. Yes
* c. No

## Chapter 3 Exercise
1. @see Chapter3Exercise.java
2. @see Chapter3Exercise.java
3.
* a. Eager
* b. Lazy
4.
* a. Yes, higher-order because it takes a function as the argument.
* b. No, higher-order because argument or return is not a function
5. a side-effect free
6. @see Chapter2Exercise.java

## Chapter 4 Exercise
1. @see Chapter4Exercise.java
2. No, you cannot overwrite the equals and hashCode method in a default method, because this two classes are defined in the Object class, so definition to the subclass will preserve
3. @see Artists.java