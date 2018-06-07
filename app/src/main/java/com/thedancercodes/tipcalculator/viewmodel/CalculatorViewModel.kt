package com.thedancercodes.tipcalculator.viewmodel

import android.content.ContentValues.TAG
import android.databinding.BaseObservable
import android.util.Log
import com.thedancercodes.tipcalculator.model.Calculator
import com.thedancercodes.tipcalculator.model.TipCalculation

/**
 * ViewModel will perform the logic to validate the input strings from the View
 * before it passes it to the Model.
 *
 * It will also do the work to translate the Strings to Double & Int objects as necessary.
 */
class CalculatorViewModel(val calculator: Calculator = Calculator()) : BaseObservable() {

    // Variable and Actions that the View can bind to and call directly.
    var inputCheckAmount = ""

    var inputTipPercentage = ""

    // Variable for tipCalculation & View combined directly to it.
    var tipCalculation = TipCalculation()


    /**
     * When FAB is clicked, we work with the model to perform the calculation.
     *
     * This function is going to invoke a method on the Model.
     *
     * We need to have the Model as a dependency for this class:
     *  Line 12 : Pull in this model as a constructor arg & give it a default value of Calculator
     *  if nothing is provided.
     *
     */
    fun calculateTip() {

        // Logs to show that we invoke the calculateTip() function on the VM when FAB is clicked.
        Log.d(TAG, "calculateTipInvoked")

        // Validate and translate the values that will be set by the View into the required types:
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()


        // If the values checkAmount & tipPct are not null,
        // perform the Models tip calculation method on them
        if (checkAmount != null && tipPct != null) {

            Log.d(TAG, "CheckAmount: $checkAmount, TipPercentage: $tipPct")

            tipCalculation = calculator.calculateTip(checkAmount, tipPct)
            clearInputs()

        }
    }

    fun clearInputs() {
        inputCheckAmount = "0.00"
        inputTipPercentage = "0"

        // Method provided by the BaseObservable base class
        // that indicates all properties might have changed.

        // Called after we have updated both of our inputs.
        notifyChange()
    }
}