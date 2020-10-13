package basic.thread;

public class Deadlock {

  private Object key1 = new Object();
  private Object key2 = new Object();

  public static void main(String[] args) throws InterruptedException {
    Deadlock deadlock = new Deadlock();
    Thread thread1 = new Thread(() -> deadlock.taskA());
    Thread thread2 = new Thread(() -> deadlock.taskB());
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();
  }

  public void taskA() {
    synchronized (key1) {
      System.out.println(Thread.currentThread().getName() + " Task A");
      taskB();
    }
  }

  public void taskB() {
    synchronized (key2) {
      System.out.println(Thread.currentThread().getName() + " Task B");
      taskC();
    }
  }

  private void taskC() {
    synchronized (key1) {
      System.out.println(Thread.currentThread().getName() + " Task C");
    }
  }
}
