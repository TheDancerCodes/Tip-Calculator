package com.thedancercodes.tipcalculator.model

// Model object we will use to store a calculated tip result
// This model object stores each of the outputs in numerical form
data class TipCalculation(
        val locationName: String = "",
        val checkAmount: Double = 0.0,
        val tipPct: Int = 0,
        val tipAmount: Double = 0.0,
        val grandTotal: Double = 0.0
)