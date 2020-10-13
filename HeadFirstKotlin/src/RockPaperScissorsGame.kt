fun main() {
    val options = arrayOf("Rock", "Paper", "Scissors")
    val gameChoice = getGameChoice(options)
    val userChoice = getUserChoice(options)
    printResult(userChoice, gameChoice)
}

fun getGameChoice(options: Array<String>): String = options[(Math.random() * options.size).toInt()]

fun getUserChoice(options: Array<String>): String {
    var userChoice = ""
    var isValidChoice = false
    while (!isValidChoice) {
        print("Please enter one of the following:")
        for (choice in options) print(" $choice")
        println(".")
        val userInput = readLine()

        if (userInput != null && options.contains(userInput)) {
            isValidChoice = true
            userChoice = userInput
        } else {
            println("You must enter a valid choice.")
        }
    }
    return userChoice
}

fun printResult(userChoice: String, gameChoice: String) {
    var result: String
    if (userChoice == gameChoice) {
        result = "Tie!"
    } else if ((userChoice == "Scissors" && gameChoice == "Paper")
        || (userChoice == "Rock" && gameChoice == "Scissors")
        || (userChoice == "Paper" && gameChoice == "Rock")
    ) {
        result = "You win!"
    } else {
        result = "You lose!"
    }
    println("You chose $userChoice. I chose $gameChoice. $result")
}
