// getting groovy
// hello world to try out groovy sintax

class MyClass{
  void doSomething(Closure clorsure){
    clorsure.call()
  }
}

obj = new MyClass()
obj.doSomething {
  println("Hello World")
}