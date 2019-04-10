

fun main(){
  val ints = listOf(1,2,3,4,5,6,7,8)
  // val smallInts = ints.filter {it<4}
  // for(i:Int in smallInts) println(i)
  ints
    .filter {it<4}
    .map {it*it}
    .forEach(::println)
}