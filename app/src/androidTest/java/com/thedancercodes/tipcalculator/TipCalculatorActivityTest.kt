package com.thedancercodes.tipcalculator

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.thedancercodes.tipcalculator.view.TipCalculatorActivity
import org.junit.Rule
import org.junit.Test

/**
 * Created by TheDancerCodes on 11/06/2018.
 */
class TipCalculatorActivityTest {

    // Rule to automatically create and start the TipCalculatorActivity before each test runs
    // & finish it when each test finishes.
    @get:Rule
    var activityTestRule = ActivityTestRule(TipCalculatorActivity::class.java)

    @Test
    fun testTipCalculator() {

        // Calculate Tip and assert outputs
        enter(checkAmount = 15.99, tipPercent = 15)
        calculateTip()
        assertOutput(name = "", checkAmount = "$15.99", tipAmount = "$2.40", total = "$18.39")

        // Save Tip & Assert that the Location name is displayed
        saveTip(name = "Planet Yogurt")
        assertOutput(name = "Planet Yogurt", checkAmount = "$15.99", tipAmount = "$2.40", total = "$18.39")

        // Clear Outputs
        clearOutputs()
        assertOutput(name = "", checkAmount = "$0.00", tipAmount = "$0.00", total = "$0.00")

        // Load Tip just saved, validating that the data is loaded.
        loadTip(name = "Planet Yogurt")
        assertOutput(name = "Planet Yogurt", checkAmount = "$15.99", tipAmount = "$2.40", total = "$18.39")


    }

    // View Action: Enter Check Amount and Tip Percentage
    private fun enter(checkAmount: Double, tipPercent: Int) {
        onView(withId(R.id.input_check_amount)).perform(replaceText(checkAmount.toString()))
        onView(withId(R.id.input_tip_percentage)).perform(replaceText(tipPercent.toString()))
    }

    // View Action: Calculate Tip
    private fun calculateTip() {
        onView(withId(R.id.calculate_fab)).perform(click())
    }

    // View Assertion: Assert that the text matches the values passed into the function
    private fun assertOutput(name: String, checkAmount: String, tipAmount: String, total: String) {
        onView(withId(R.id.bill_amount)).check(matches(withText(checkAmount)))
        onView(withId(R.id.tip_amount)).check(matches(withText(tipAmount)))
        onView(withId(R.id.grand_total)).check(matches(withText(total)))
        onView(withId(R.id.calculation_name)).check(matches(withText(name)))
    }

    private fun clearOutputs() {
        enter(checkAmount = 0.0, tipPercent = 0)
        calculateTip()
    }

    private fun saveTip(name: String) {

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getContext())

        onView(withText(R.string.action_save)).perform(click())

        // Set text to name we were given
        onView(withHint(R.string.save_hint)).perform(replaceText(name))

        onView(withText(R.string.action_save)).perform(click())

    }

    private fun loadTip(name: String) {

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getContext())

        onView(withText(R.string.action_load)).perform(click())

        onView(withText(name)).perform(click())
    }
}