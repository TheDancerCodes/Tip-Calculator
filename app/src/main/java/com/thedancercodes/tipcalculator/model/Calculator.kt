package com.thedancercodes.tipcalculator.model

import android.arch.lifecycle.LiveData
import java.math.RoundingMode

// Add TipCalculationRepository as a dependency in the constructor
class Calculator(val repository: TipCalculationRepository = TipCalculationRepository()) {

    fun calculateTip(checkAmount: Double, tipPct: Int) : TipCalculation {

        val tipAmount = (checkAmount * (tipPct.toDouble() / 100.0))
                .toBigDecimal()
                .setScale(2, RoundingMode.HALF_UP)
                .toDouble()

        val grandTotal = checkAmount + tipAmount

        return TipCalculation(
                checkAmount = checkAmount,
                tipPct = tipPct,
                tipAmount = tipAmount,
                grandTotal = grandTotal
        )
    }

    // Save and Load functions tha delegate work to the repository
    fun saveTipCalculation(tc: TipCalculation) {
        repository.saveTipCalculation(tc)
    }

    fun loadTipCalcuclationByLocationName(locationName: String) : TipCalculation? {
        return repository.loadTipCalculationByName(locationName)
    }

    fun loadSavedTipCalculations() : LiveData<List<TipCalculation>> {
        return repository.loadSavedTipCalculations()
    }

}