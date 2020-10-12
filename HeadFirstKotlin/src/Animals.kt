abstract class Animal : Roamable {
    abstract val image: String
    abstract val food: String
    abstract val habitat: String
    var hunger = 10
    abstract fun makeNoise()
    abstract fun eat()

    fun sleep() {
        println("The Animal is sleeping")
    }


    override fun roam() {
        println("Animal roaming...")
    }
}

interface Roamable {
    fun roam()
}

class Hippo : Animal() {
    override val image = "hippo.jpg"
    override val food = "grass"
    override val habitat = "water"

    override fun makeNoise() {
        println("Grunt! Grunt!")
    }

    override fun eat() {
        println("The Hippo is eating $food")
    }
}

abstract class Canine : Animal() {
    override fun roam() {
        println("The Canine is roaming")
    }
}

class Wolf : Canine() {
    override val image = "wolf.jpg"
    override val food = "meat"
    override val habitat = "forests"

    override fun makeNoise() {
        println("Hoooowl!")
    }

    override fun eat() {
        println("The Wolf is eating $food")
    }
}

class Vet {
    fun giveShot(animal: Animal) {
        // code to so medical things...
        animal.makeNoise()
    }
}

class Vehicle : Roamable {
    override fun roam() {
        println("Vehicle roaming...")
    }
}

fun main() {
    val animals = arrayOf(Hippo(), Wolf())
    val vet = Vet()
    for (animal in animals) {
        animal.roam()
        animal.eat()
        vet.giveShot(animal)
    }

    val roaming = arrayOf(Hippo(), Wolf(), Vehicle())

    for (roamer in roaming) {
        roamer.roam()
        if (roamer is Animal) {
            roamer.eat()
        }
    }
}