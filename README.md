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
1. @see Chapter2Exercise.java
2. @see Chapter2Exercise.java
3.
* a. Eager
* b. Lazy
4.
* a. higher-order because it takes a function as the argument.
* b. no higher-order because argument or return is not a function
5. a side-effect free
6. @see Chapter2Exercise.java
