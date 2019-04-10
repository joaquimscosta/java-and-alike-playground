import java.io.File
import java.time.Instant

fun main() {
//    val name: String by lazy { getName() }
    val name: String by lazy(::getName)
    val name2: String = name
    println("Hello, $name!")
    println(name2)
    println(name)
    println(name2)

    val people = listOf(Person("Joaquim"), Person("Nancy"), Person("Camila"), Person("Thallia"))
    show(people)

    // will throw exception since not implemented
    try {
        Person.create("blah")
    } catch (e: NotImplementedError) {
        println("\n***${e.message}***\n")
    }

    // Kotlin Collections are immutable by default
    // but noticed that you can still do operations on them
    val people2 = people + Person("Carlos")
    println("people = $people")
    println("people2 = $people2")

    println("sumOfPrimes(10) = ${sumOfPrimes(10)}")

    // 0 to 10 (including) count by 2
    for (i in 0 until 10 step 2) print("$i, ")
    println()

    // this is range expression, same structure as above
    val range = 0 until 15 step 3
    for (i in range) print("$i, ")
    println()


    for (i in 0.until(20).step(4)) print("$i, ")
    println()

    for (i in 25.downTo(0).step(5)) print("$i, ")
    println()
    readAndDisplayFile("chapter2.txt")

    val multiline = """This is the first line
        |and this is the second line
    """.trimMargin()
    println(multiline)
}


fun getName(): String {
    println("entering getName")
    return "World"
}


data class Person(val name: String, val registeredOn: Instant = Instant.now()) {
    // this is kind of the static values in kotlin
    // methods and variables enclosed in companion... are exposed at class level
    companion object {
        fun create(xml: String) {
            TODO("method to create person from from an xml string")
        }
    }
}

fun show(persons: List<Person>) {
    // this is using destructure to to get the values from Person object
    for ((name, registered) in persons) {
        println("$name registered on $registered.")
    }
}

fun sumOfPrimes(limit: Long): Long {
    val seq: Sequence<Long> = sequenceOf(2L) +
            generateSequence(3L) { it + 2 }
                    .takeWhile {
                        it < limit
                    }

    fun isPrime(n: Long): Boolean =
            seq.takeWhile {
                it * it <= n
            }.all {
                n % it != 0L
            }
    return seq.filter(::isPrime).sum()
}

//
fun readAndDisplayFile(path: String) {
    val lines: List<String> = File(ClassLoader.getSystemResource(path).file)
            .inputStream()
            .use {
                it.bufferedReader()
                        .lineSequence()
                        .toList()
            }
    lines.forEach(::println)

    println("***************")
    // or this other way
    File(ClassLoader.getSystemResource(path).file).forEachLine { println(it) }
}



