package com.thedancercodes.tipcalculator.model


class RestaurantCalculator {
    fun calculateTip(checkAmount: Double, tipPct: Int) : TipCalculation {

        val tipAmount = checkAmount * (tipPct.toDouble() / 100.0)

        val grandTotal = checkAmount + tipAmount

        return TipCalculation(
                checkAmount = checkAmount,
                tipPct = tipPct,
                tipAmount = tipAmount,
                grandTotal = grandTotal
        )
    }

}