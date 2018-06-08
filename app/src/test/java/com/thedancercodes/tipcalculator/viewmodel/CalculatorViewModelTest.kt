package com.thedancercodes.tipcalculator.viewmodel

import android.app.Application
import com.thedancercodes.tipcalculator.R
import com.thedancercodes.tipcalculator.model.Calculator
import com.thedancercodes.tipcalculator.model.TipCalculation
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

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

    // Mocking the Calculator Model
    @Mock
    lateinit var mockCalculator: Calculator

    // Mock an Application Object
    @Mock
    lateinit var application: Application


    // Re-initialize the ViewModel before every test.
    @Before
    fun setup() {
        // Initialize any member variables in this class that are annotated with @Mock making them
        // Mock objects.
        MockitoAnnotations.initMocks(this)

        stubResource(0.0, "$0.00")

        // CalculatorViewModel Constructor
        calculatorViewModel = CalculatorViewModel(application, mockCalculator)
    }

    // Helper function: Given a Double returns the expected String result
    private fun stubResource(given: Double, returnStub: String) {
        `when`(application.getString(R.string.dollar_amount, given)).thenReturn(returnStub)
    }

    // Simple test that sets the inputs on the ViewModel, invokes calculateTip() on the ViewModel's
    // and validates/asserts the expected output in the ViewModel tipCalculation output variable
    @Test
    fun testCalculateTip() {

        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = "15"

        // When the View Model calls calculateTip() on the Calculator with the inputs we expect
        // we can provide a stub TipCalculation in response & assert that the stub gets set as the
        // output TipCalculation.
        val stub = TipCalculation(checkAmount = 10.00, tipAmount = 1.5, grandTotal = 11.5)
        `when`(mockCalculator.calculateTip(10.00, 15)).thenReturn(stub)

        stubResource(10.0, "$10.00")
        stubResource(1.5, "$1.50")
        stubResource(11.5, "$11.50")

        calculatorViewModel.calculateTip()


        // Assert that output strings set up in the ViewModel match our expectation
        // based on this calculation
        assertEquals("$10.00", calculatorViewModel.outputCheckAmount)
        assertEquals("$1.50", calculatorViewModel.outputTipAmount)
        assertEquals("$11.50", calculatorViewModel.outputTotalDollarAmount)
    }

    // Validate that the View Model doesn't call calculateTip() on the Model
    // if the inputs are invalid

    @Test
    fun testCalculateTipBadTipPercent() {

        // Given a bad tip percentage
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = "" // Empty string is not a valid Int

        calculatorViewModel.calculateTip()

        // Validate the calculateTip() never gets called for any Double or Int
        verify(mockCalculator, never()).calculateTip(anyDouble(), anyInt())

    }

    @Test
    fun testCalculateTipBadCheckInputAmount() {

        // Given a bad check amount
        calculatorViewModel.inputCheckAmount = "" // Empty string is not a valid Double
        calculatorViewModel.inputTipPercentage = "15"

        calculatorViewModel.calculateTip()

        // Validate the calculateTip() never gets called for any Double or Int
        verify(mockCalculator, never()).calculateTip(anyDouble(), anyInt())

    }

}