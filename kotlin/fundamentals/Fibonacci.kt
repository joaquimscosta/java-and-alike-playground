fun main(){
   fibonacci(action=object : Action {
    override fun doAction(value:Int):Unit{
      println(value)
    }
  })
  println("-----------")
  // fibonacci2(10, {n->println(n)})
  fibonacci2(10, ::println)

}

interface Action{
  fun doAction(value: Int):Unit
}

fun fibonacci(limit:Int=8, action:Action){
  var prev = 0
  var prevprev = 0
  var current= 1
  for(i:Int in 1..limit){
    action.doAction(current)
    var temp = current
    prevprev = prev
    prev = temp
    current = prev + prevprev
  }
}

fun fibonacci2(limit:Int=8, action:(Int)->Unit){
  var prev = 0
  var prevprev = 0
  var current= 1
  for(i:Int in 1..limit){
    action(current)
    var temp = current
    prevprev = prev
    prev = temp
    current = prev + prevprev
  }
}