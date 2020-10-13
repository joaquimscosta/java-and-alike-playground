
package basic.thread;

public class SimpleThread {

  public static void runMe() {
    new Thread(() -> System.out.println("Hello World")).start();
  }

  public static void main(String[] args) {
    SimpleThread.runMe();
    sleepSeconds(5);
  }

  private static void sleepSeconds(long seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
