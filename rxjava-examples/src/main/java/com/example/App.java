package com.example;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.reactivex.functions.Consumer;


/**
 * Hello world!
 */
public class App {

  private static final List<String> NAMES = Arrays.asList("Joaquim", "Nancy", "Thallia", "Camila");


  public static void main(String[] args) {
    System.out.println("Hello World!");
  }


  public static void example1() {
    // The Observable is a push-based, composable iterator.
    // A given Observable<T> pushes (emits) things of type T through a series of operators until it arrives at an Observer that consumes the items.
    Observable<String> myStrings = Observable.just("Joaquim", "Nancy", "Thallia", "Camila");
    myStrings.subscribe(System.out::println);

    myStrings
        .map(String::length)
        .subscribe(System.out::println);
  }


  public static void example2() {
    // emits in 1 second intervals
    Observable.interval(1, TimeUnit.SECONDS).subscribe(seconds -> System.out.println(seconds));

    // Hold main thread so Observable has chance to fire
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void example3() {
    // Observable works by passing three types of events (abstract methods in the Observer class):
    // onNext(): Passes each item one at a time from the source Observable all the way down to the Observer.
    // onComplete(): Communicates a completion event all the way down to the Observer, indicating that no more onNext() calls will occur.
    // onError(): Communicates an error up the chain to the Observer, where the Observer typically defines how to handle it.

    // The Observable.create() factory allows us to create an Observable by providing a lambda receiving an Observable emitter.
    Observable.create(emitter -> {
      try {
        emitter.onNext("Joaquim");
        emitter.onNext("Nancy");
        emitter.onNext("Camila");
        emitter.onNext("Thallia");
        emitter.onComplete();
      } catch (Exception e) {
        emitter.onError(e);
      }
    }).subscribe(name -> System.out.println("Received: " + name));
  }

  public static void example4() {
    // onNext, onComplete, onError, can also push to an operator service as the next step in chain.
    Observable<String> source = Observable.create(emitter -> {
      try {
        emitter.onNext("Joaquim");
        emitter.onNext("Nancy");
        emitter.onNext("Camila");
        emitter.onNext("Thallia");
        emitter.onComplete();
      } catch (Exception e) {
        emitter.onError(e);
      }
    });
    source.map(String::length)
        .filter(length -> length > 7)
        .subscribe(length -> System.out.println("Received: " + length));
  }


  public static void example5() {
    Observable<String> source = Observable.just("Joaquim", "Nancy", "Thallia", "Camila");
    source.map(String::length)
        .filter(length -> length > 7)
        .subscribe(length -> System.out.println("Received: " + length));

  }

  public static void example6() {
    Observable<String> source = Observable.fromIterable(NAMES);
    source.map(String::length)
        .filter(length -> length > 7)
        .subscribe(length -> System.out.println("Received: " + length));
  }

  public static void example7() {
    Observable<String> source = Observable.fromIterable(NAMES);
    Observer<Integer> myObserver = new Observer<Integer>() {
      @Override
      public void onSubscribe(Disposable disposable) {
        // TODO: do nothing yet
      }

      @Override
      public void onNext(Integer value) {
        System.out.println("Received: " + value);
      }

      @Override
      public void onError(Throwable e) {
        e.printStackTrace();
      }

      @Override
      public void onComplete() {
        System.out.println("Done!");
      }
    };
    source.map(String::length).filter(length -> length > 7).subscribe(myObserver);
  }

  public static void example8() {

    // Implementing an Observer is a bit verbose. The subscriber method is overloaded to accept lambda arguments for our three events.
    // onNext, onError, onComplete
    Observable<String> source = Observable.fromIterable(NAMES);

    Consumer<Integer> onNext = length -> System.out.println("Received: " + length);
    Action onComplete = () -> System.out.println("Done!");
    Consumer<Throwable> onError = Throwable::printStackTrace;

    source.map(String::length).filter(length -> length > 7).subscribe(onNext, onError, onComplete);
  }

  public static void example9() {

  }

  public static void example10() {

  }

  public static void example11() {

  }

}