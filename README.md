# Java Lambdas
This project will contain example of lambdas function feature from Java 8

## Chapter 2 Exercise
1.
```
public interface Function<T,R>{
    R apply(T t)
}
```
    a. T ---> Function ---> R
    b. This function could be used for **absolute value function**
    c. `(x,y) -> x + 1`

2. `ThreadLocal.withInitial(()->new SimpleDateFormat("dd-MMM-yyyy"))`
3.
    a. Yes
    b. Yes
    c. No