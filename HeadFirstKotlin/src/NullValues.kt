class Wolff {
    var hunger = 10
    val food = "meat"

    fun eat() {
        println("The wolff is eating $food")
    }
}

class MyWolff {
    var wolff: Wolff? = Wolff()

    fun myFunction() {
        wolff?.eat()
    }
}

fun getAlphaWolf(): Wolff? {
    return Wolff()
}

fun main() {
    var w: Wolff? = Wolff()
    if (w != null) {
        w.eat()
    }

    var x = w?.hunger
    println("The value of x is $x")

    var y = w?.hunger ?: -1
    println("The value of y is $y")

    var myWolf = MyWolff()
    myWolf?.wolff?.hunger = 8
    println("The value of myWolf?.wolff?.hunger is ${myWolf?.wolff?.hunger}")

    var myArray = arrayOf("Hi", "Hello", null)
    for (item in myArray) {
        item?.let { println(it) }
    }

    getAlphaWolf()?.let {
        it.eat()
    }
    w = null
    var z = w!!.hunger
}

