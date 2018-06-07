package com.thedancercodes.tipcalculator.viewmodel

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by TheDancerCodes on 06/06/2018.
 */
class CalculatorViewModelTest {

    /**
     * CURRENT STATE:
     * The ViewModel has one function currently that validates and translates input values from the
     * View into the types required by the Model. fun calculateTip()
     *
     *
     * If they are valid numbers, it invokes calculateTip() as the function on the Model,
     * setting the result into an output variable for the View
     */


    lateinit var calculatorViewModel: CalculatorViewModel

    // Re-initialize the ViewModel before every test.
    @Before
    fun setup() {
        calculatorViewModel = CalculatorViewModel()
    }

    // Simple test that sets the inputs on the ViewModel, invokes calculateTip() on the ViewModel's
    // and validates/asserts the expected output in the ViewModel tipCalculation output variable
    @Test
    fun testCalculateTip() {

        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = "15"

        calculatorViewModel.calculateTip()

        assertEquals(10.00, calculatorViewModel.tipCalculation.checkAmount)
        assertEquals(1.50, calculatorViewModel.tipCalculation.tipAmount)
        assertEquals(11.50, calculatorViewModel.tipCalculation.grandTotal)
    }



}