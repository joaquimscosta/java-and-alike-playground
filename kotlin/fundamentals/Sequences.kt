
fun main(){
  val people = listOf(Person("Joaquim"), Person("Nancy"), Person("Camila"), Person("Thallia"))
  val names = people
    .asSequence()
    .filter {it.name.length < 6}
    .map{it.name.toUpperCase()}
    .toList()

    for( name in names) println(name)
}

data class Person(val name:String)