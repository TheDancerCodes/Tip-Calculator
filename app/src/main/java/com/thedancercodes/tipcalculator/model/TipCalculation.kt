package com.thedancercodes.tipcalculator.model

// Model object we will use to store a calculated tp result
data class TipCalculation(
        val checkAmount: Double = 0.0,
        val tipPct: Int = 0,
        val tipAmount: Double = 0.0,
        val grandTotal: Double = 0.0
)