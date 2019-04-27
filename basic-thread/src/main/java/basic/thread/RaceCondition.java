package basic.thread;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RaceCondition {

  public static void main(String[] args) throws InterruptedException {
    LongWrapper counter = new LongWrapper(0l);
    incrementTo1Million(() -> range().forEach(value -> counter.increment()));
    // expected value is 1_000_000, but wont happen because race condition
    System.out.println("counter = " + counter.getValue());

    LongWrapperThreadSafe counterThreadSafe = new LongWrapperThreadSafe(0l);
    incrementTo1Million(() -> range().forEach(value -> counterThreadSafe.increment()));
    // expected value is 1_000_000
    System.out.println("counterThreadSafe = " + counterThreadSafe.getValue());
  }

  private static void incrementTo1Million(Runnable runnable) throws InterruptedException {
    List<Thread> threads = range().mapToObj(value -> {
      Thread t = new Thread(runnable);
      t.setName("Thread-" + value);
      t.start();
      return t;
    }).collect(Collectors.toList());
    for (Thread t : threads) {
      t.join();
    }
  }

  private static IntStream range() {
    return IntStream.range(0, 1000);
  }

  // this class is not thread safee
  static class LongWrapper {

    private long value;

    public LongWrapper(long value) {
      this.value = value;
    }

    public void increment() {
      this.value++;
    }

    public long getValue() {
      return value;
    }
  }

  // this class is thread safe
  static class LongWrapperThreadSafe {

    private volatile Object key = new Object();
    private long value;

    public LongWrapperThreadSafe(long value) {
      this.value = value;
    }

    public void increment() {
      synchronized (key) {
        this.value++;
      }
    }

    public long getValue() {
      return value;
    }
  }
}
