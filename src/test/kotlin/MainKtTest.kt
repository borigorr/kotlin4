import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calcCommissionDefaultParams() {
        val amount = 100.0
        val result = calcCommission(amount)
        assertEquals(0.0, result, 0.0)
    }

    @Test(expected = LimitException::class)
    fun calcCommissionVkPayMaxAmount() {
        val amount = 15_001.0
        val amountCurrentMonth = 500.0
        val cardType = "VKPay"
        calcCommission(amount, amountCurrentMonth, cardType)
    }

    @Test(expected = LimitException::class)
    fun calcCommissionVkPayMaxAmountCurrentMonth() {
        val amount = 1_001.0
        val amountCurrentMonth = 40_001.0
        val cardType = "VKPay"
        calcCommission(amount, amountCurrentMonth, cardType)
    }

    @Test(expected = LimitException::class)
    fun calcCommissionCardMaxAmountCurrentMonth() {
        val amount = 1_001.0
        val amountCurrentMonth = 600_001.0
        val cardType = "MasterCard"
        calcCommission(amount, amountCurrentMonth, cardType)
    }

    @Test(expected = LimitException::class)
    fun calcCommissionCardMaxAmount() {
        val amount = 150_001.0
        val amountCurrentMonth = 60_001.0
        val cardType = "MasterCard"
        calcCommission(amount, amountCurrentMonth, cardType)
    }

    @Test
    fun calcCommissionMasterCard() {
        val amount = 100.0
        val amountCurrentMonth = 75_001.0
        val cardType = "MasterCard"
        val result = calcCommission(amount, amountCurrentMonth, cardType)
        assertEquals(20.6, result, 0.0)
    }

    @Test
    fun calcCommissionMaestroCard() {
        val amount = 100.0
        val amountCurrentMonth = 75_001.0
        val cardType = "Maestro"
        val result = calcCommission(amount, amountCurrentMonth, cardType)
        assertEquals(20.6, result, 0.0)
    }

    @Test
    fun calcCommissionVisaMin() {
        val amount = 100.0
        val amountCurrentMonth = 50.0
        val cardType = "Visa"
        val result = calcCommission(amount, amountCurrentMonth, cardType)
        assertEquals(35.0, result, 0.0)
    }
}