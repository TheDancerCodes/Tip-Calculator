package com.thedancercodes.tipcalculator.viewmodel

import android.app.Application
import android.databinding.BaseObservable
import com.thedancercodes.tipcalculator.R
import com.thedancercodes.tipcalculator.model.Calculator
import com.thedancercodes.tipcalculator.model.TipCalculation

/**
 * ViewModel will perform the logic to validate the input strings from the View
 * before it passes it to the Model.
 *
 * It will also do the work to translate the Strings to Double & Int objects as necessary.
 */
class CalculatorViewModel @JvmOverloads constructor(
        app: Application, val calculator: Calculator = Calculator()) : ObservableViewModel(app) {

    // Private Variable to store last calculated tip
    private var lastTipCalculated = TipCalculation()

    // Variable and Actions that the View can bind to and call directly.
    var inputCheckAmount = ""

    var inputTipPercentage = ""

    // Output Variables
    val outputCheckAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.checkAmount)
    val outputTipAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.tipAmount)
    val outputTotalDollarAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.grandTotal)
    val locationName get() = lastTipCalculated.locationName

    // Initialization Block
    init {

        // Pass in an empty tip calculation which will initially set the variables to 0.00
        updateOutputs(TipCalculation())

    }

    // Encapsulation of work in private function
    private fun updateOutputs(tc: TipCalculation) {
        // update the last tip calculated
        lastTipCalculated = tc
        notifyChange()
    }

    fun saveCurrentTip(name: String) {

        // CurrentTipCalculation with LocationName added
        val tipToSave = lastTipCalculated.copy(locationName = name)

        calculator.saveTipCalculation(tipToSave)

        updateOutputs(tipToSave)
//        notifyChange()

    }

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

        // Validate and translate the values that will be set by the View into the required types:
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPct = inputTipPercentage.toIntOrNull()


        // If the values checkAmount & tipPct are not null,
        // perform the Models tip calculation method on them
        if (checkAmount != null && tipPct != null) {

            // Update output values each time we calculate a tip
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
        }
    }
}