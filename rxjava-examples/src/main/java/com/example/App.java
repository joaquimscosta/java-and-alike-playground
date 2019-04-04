package com.example;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.observables.ConnectableObservable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import io.reactivex.functions.Consumer;


/**
 * Hello world!
 */
public class App {

  private static final List<String> NAMES = Arrays.asList("Joaquim", "Nancy", "Thallia", "Camila");
  private static final Action ON_COMPLETE = () -> System.out.println("Done!");

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
    sleep(5000);
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
    // Cold Observable
    Observable<String> source = Observable.fromIterable(NAMES);

    // first observer
    source.subscribe(name -> System.out.println("Observer Received 1: " + name));

    // second observer
    source.subscribe(name -> System.out.println("Observer Received 2: " + name));
  }

  public static void example10() {
    // Making cold observable into (hot)
    ConnectableObservable<String> source = Observable.fromIterable(NAMES).publish();

    // first observer
    source.subscribe(name -> System.out.println("Observer 1: " + name));

    // second observer
    source
        .map(String::length)
        .subscribe(name -> System.out.println("Observer 2: " + name));

    source.connect();
  }

  public static void example11() {
    // Observable.range() : emit each number from a start value and increment each emission until the specified count is reached
    // the two arguments for Observable.range() are not lower/upper bounds
    Observable.range(1, 10).subscribe(System.out::println);
  }

  public static void example12() {
    // Observable.interval(): emit a consecutive long emission (starting at 0) at every specified time interval
    Observable.interval(1, TimeUnit.SECONDS)
        .subscribe(second -> System.out.println(second + " Mississippi"));
    sleep(5000);
  }


  public static void example13() {
    // Observable.future() : RxJava Observables are much more robust
    // and expressive than Futures, but if you have existing libraries that yield Futures,
    // you can easily turn them into Observables via Observable.future()
    Future<String> futureTask = new FutureTask<>(() -> System.out.println("noop future task"),
        "Future Task Completed!");
    Observable.fromFuture(futureTask)
        .subscribe(result -> System.out.println("Received: " + result));
  }


  public static void example14() {
    // Note that no emissions were printed because there were none.
    // It went straight to calling onComplete,
    // which printed the Done! message in the Observer
    // ...(onNext, onError, onComplete)
    Observable.empty().subscribe(
        System.out::println,
        Throwable::printStackTrace,
        () -> System.out.println("Done!")
    );
  }


  public static void example15() {
    // Observable.never() : similar to ...empty() in addition,
    // will never invoke onComplete(), and never emitting, ...making this Observable infinite
    // ... primarily used for testing purpose
    Observable.never().subscribe(
        System.out::println,
        Throwable::printStackTrace,
        () -> System.out.println("Done!")
    );

    sleep(5000);
  }

  public static void example16() {
    //  Observable.error() : create an Observable that immediately calls onError() with a specified exception
    // ... primarily used for testing purpose
    Observable.error(new Exception("Ouch... Error!")).subscribe(
        System.out::println,
        Throwable::printStackTrace,
        ON_COMPLETE
    );
  }

  static int start = 5;
  static int count = 10;

  public static void example17() {
    // Observable.defer() : ability to create a separate state for each Observer.
    // when your source is stateful and you want to create a separate state for each Observer,
    // Observable may not capture something that has changed about its parameters and send emissions that are obsolete
    Observable<Integer> source = Observable.defer(() -> Observable.range(start, count));
    source.subscribe(System.out::println);
    // modifying count, now should be 5, 6, 7
    count = 3;
    source.subscribe(System.out::println);
  }


  public static void example18() {
    // Observable.fromCallable() : if a procedure throws an error, we want it to be emitted up the Observable chain through onError()
    // rather than throw the error at that location in traditional Java fashion.
    // For instance, if you try to wrap Observable.just() around an expression that divides  1 by 0,
    // the exception will be thrown, not emitted up to Observer. We resolve this issue by using .... fromCallable()
    // BAD --> Observable.just(1/0).subscribe(System.out::println, Throwable::printStackTrace);
    Observable.fromCallable(() -> 1 / 0).subscribe(
        System.out::println,
        e -> System.out.println("onError: " + e.getMessage())
    );
  }


  public static void example19() {
    //Single<T> is essentially an Observable<T> that will only emit one item.
    // It works just like an Observable, but it is limited only to operators that make sense for a single emission
    Observable.fromIterable(NAMES)
        .single("Oops")
        .subscribe(System.out::println, Throwable::printStackTrace);
    // Better yet
    Single.just(NAMES.get(0)).subscribe(System.out::println, Throwable::printStackTrace);
  }


  public static void example20() {
    // Maybe is just like a Single except that it allows no emission to occur (hence Maybe), 0 or 1 time

    // has emission
    Maybe.just(100).subscribe(System.out::println, Throwable::printStackTrace, ON_COMPLETE);

    // no emission
    Maybe.empty().subscribe(System.out::println, Throwable::printStackTrace, ON_COMPLETE);

    // firstElement returns a Maybe
    // this emit 0 (empty) or 1, (but we know it will emit because we have items in the NAMES)
    Observable.just(NAMES)
        .firstElement()
        .subscribe(
            (name) -> System.out.println("Received: " + name),
            Throwable::printStackTrace,
            ON_COMPLETE);
  }


  public static void example21() {
    // Completable is simply concerned with an action being executed,
    // but it does not receive any emissions. It does not have onNext()
    // or onSuccess() to receive emissions, but it does have onError() and onComplete()

    // will invoke onComplete immediately
    Completable.complete();

    // execute the action passed the runnable, then invoke onComplete
    Completable.fromRunnable(() -> System.out.print("Running some process")).subscribe(ON_COMPLETE);
  }


  public static void example22() {
    //The Disposable is a link between an Observable and an active Observer,
    // and you can call its dispose() method to stop emissions and dispose
    // of all resources used for that Observer
    Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);
    Disposable disposable = seconds
        .subscribe((second) -> System.out.println("Received: " + second));

    sleep(5000);

    // this will stop emissions
    disposable.dispose();

    // wait 5 seconds to show emission stopped
    sleep(5000);
  }


  public static void example23() {



  }


  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}