fun main() {
    add(1, 5)


    val myCreditCard = CreditCard(name = "Joaquim Costa", cardNumber = "1232-1232-2133")
    println("myCreditCard = $myCreditCard")
    myCreditCard.charge(buyDonuts(creditCard = myCreditCard).payment.amount)
    println("myCreditCard after buying 1 donut = $myCreditCard")

    myCreditCard.charge(buyDonuts(5, myCreditCard).payment.amount)
    println("myCreditCard after buying 5 donut = $myCreditCard")
}

fun add(a: Int, b: Int): Int {
    log(String.format("Returning ${a + b} as result of $a + $b"))
    return a + b
}

fun log(m: String) {
    println(m)
}

fun buyDonuts(quantity: Int = 1, creditCard: CreditCard): Purchase =
        Purchase(List(quantity) {
            Donut()
        }, Payment(creditCard, Donut.price * quantity))

class Payment(val creditCard: CreditCard, val amount: Double) {
    fun combine(payment: Payment) =
            if (creditCard == payment.creditCard)
                Payment(creditCard, amount + payment.amount)
            else
                throw IllegalStateException("Credit card dont match!")

    companion object {
        fun groupByCard(payments: List<Payment>): List<Payment> =
                payments.groupBy { it.creditCard }
                        .values.map { it.reduce(Payment::combine) }
    }
}

data class CreditCard(val name: String, val cardNumber: String, var totalCredit: Double = 500.00) {
    fun charge(amount: Double) {
        totalCredit -= amount
    }
}

class Donut {
    companion object {
        const val price = 12.0
    }
}

data class Purchase(val donuts: List<Donut>, val payment: Payment)