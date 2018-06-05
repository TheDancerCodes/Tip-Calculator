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
        val checkInput = 10.00
        val tipPctInput = 25

        val expectedTipResult = TipCalculation(
                checkAmount = checkInput,
                tipPct = 25,
                tipAmount = 2.50,
                grandTotal = 12.50
        )

        assertEquals(expectedTipResult,
                calculator.calculateTip(checkInput, tipPctInput))

    }
}