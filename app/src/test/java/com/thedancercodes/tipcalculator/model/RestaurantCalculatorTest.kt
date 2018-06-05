package com.thedancercodes.tipcalculator.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class RestaurantCalculatorTest {

    lateinit var calculator: RestaurantCalculator

    @Before
    fun setup() {
        calculator = RestaurantCalculator()
    }

    @Test
    fun testCalculateTip() {

        val baseTc = TipCalculation(checkAmount = 10.00)

        // Copies of the Base Tip  Calculation (baseTc)
        val testVals = listOf(
                baseTc.copy(tipPct = 20, tipAmount = 2.0, grandTotal = 12.00),
                baseTc.copy(tipPct = 15, tipAmount = 1.5, grandTotal = 11.50),
                baseTc.copy(tipPct = 18, tipAmount = 1.8, grandTotal = 11.80))

        // Iterate over each one of the testVals list.
        // Easier to see what we are testing & add on additional tip percentages & variations
        // if we want to later
        testVals.forEach {

            assertEquals(it,
                    calculator.calculateTip(it.checkAmount, it.tipPct))

        }
    }
}