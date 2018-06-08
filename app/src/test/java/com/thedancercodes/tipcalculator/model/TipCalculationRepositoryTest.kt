package com.thedancercodes.tipcalculator.model

import android.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class TipCalculationRepositoryTest {

    // InstantTaskExecutorRule runs some code to ensure we can test LiveData in our unit tests
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    // Reference the Repository
    lateinit var repository: TipCalculationRepository

    @Before
    fun setup() {
        repository = TipCalculationRepository()
    }

    // Simple test function that saves a tip
    @Test
    fun testSaveTip() {

        val tip = TipCalculation(locationName = "Grand Cafe",
                checkAmount = 100.0, tipPct = 25,
                tipAmount = 25.0, grandTotal = 125.0)

        // Invoke function on repository to save the tip.
        repository.saveTipCalculation(tip)

        // Assert that after we save a tip, we can get it when we query it by name.
        assertEquals(tip, repository.loadTipCalculationByName(tip.locationName))
    }

    // Test to validate that our repository can return all the tips that its stored.
    @Test
    fun testLoadSavedTipCalculations() {

        val tip1 = TipCalculation(locationName = "Planet Yogurt",
                checkAmount = 100.0, tipPct = 25,
                tipAmount = 25.0, grandTotal = 125.0)

        val tip2 = TipCalculation(locationName = "Nyama Mama",
                checkAmount = 200.0, tipPct = 50,
                tipAmount = 50.0, grandTotal = 150.0)

        // Save both tips
        repository.saveTipCalculation(tip1)
        repository.saveTipCalculation(tip2)

        // Invoke function on the Repository to fetch the Live Data & observe the Live Data forever.
        //      `observeForever:` Since there isn't a lifecycle in the test, you tell the Live Data
        //       to observe the data for the lifetime of the test.
        repository.loadSavedTipCalculations().observeForever {

            // Pass a trailing lambda that gets any updates emitted by the Live Data
            // (eg) Tip calculations we queried for.
            tipCalculations ->

            // Assert that the size of the tipCalculations list matches the number of tips in repo
            assertEquals(2, tipCalculations?.size)

        }

    }

}