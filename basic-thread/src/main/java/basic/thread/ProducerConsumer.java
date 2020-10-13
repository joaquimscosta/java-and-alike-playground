package basic.thread;

public class ProducerConsumer {


  static class Producer{
    public void produce(){
      System.out.println("Produce");
    }
  }


  static class Consumer{
    public void consume(){
      System.out.println("Consume");
    }
  }

}
