/**
  What is a higher order function?
  It is a function that takes another function as argument
  Examples:
  val sayHello: ()->Unit = {println("Hello World")}
  val multi: (Int, Int)->Int = {x, y -> x*y}

  Function Types: params goes to (->) return
  (Int, Int) -> Int

  There is a performance overhead when using higher order functions, or lambdas
  we can use inline to get aroud this issue, but not all calls can be inlined
     (lambas assinged to a variable)
 */

 val sayHello = {println("Hello, World")}
 val multi = {x:Int, y:Int->x*y}

 fun main(){
   saySomething(sayHello)
   calc(2,3, multi)
 }

 fun saySomething(talk:()->Unit){
   talk()
 }

 fun calc(x:Int, y:Int, mutliply: (x:Int,y:Int)->Int){
   println("$x*$y=${mutliply(x,y)}")
 }