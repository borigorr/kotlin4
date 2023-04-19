
fun main() {
    println(calcCommission(100.0, cardType = "Visa"))
}

fun calcCommission(
    amount: Double,
    amountCurrentMonth: Double = 0.0,
    cardType: String = "VKPay"
) = when {
    cardType == "VKPay" && (amount > 15_000 || amountCurrentMonth > 40_000) -> throw LimitException()
    cardType != "VKPay" && (amountCurrentMonth > 600_000 || amount > 150_000) -> throw LimitException()
    (cardType == "MasterCard" || cardType == "Maestro") && amountCurrentMonth > 75_000 -> amount * 0.006 + 20
    cardType == "Visa" || cardType == "Мир" -> {
        val commission: Double = amount * 0.0075;if (commission < 35) 35.0 else commission
    }
    else -> 0.0
}

