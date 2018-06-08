package com.thedancercodes.tipcalculator.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData


class TipCalculationRepository {

    // A private map to store calculated tips by their location name
    private val savedTips = mutableMapOf<String, TipCalculation>()

    // Function that saves a tip
    fun saveTipCalculation(tc: TipCalculation) {

        // InMemory Store:
        //      Add an entry into savedTips Map by the tips location name passing the
        //      value of the TipCalculation
        savedTips[tc.locationName] = tc
    }

    // Function to get tip by its location name
    fun loadTipCalculationByName(locationName: String) : TipCalculation? {
        return savedTips[locationName]
    }

    // Function to load all tips stored in the repository;
    // Returning Live Data of a List of TipCalculation Objects.
    fun loadSavedTipCalculations() : LiveData<List<TipCalculation>> {

        // Mutable Live Data instance of the required type.
        // MutableLiveData is a sub class of LiveData that allows us to mutate the data so we create 
        // the new LiveData instance
        val liveData = MutableLiveData<List<TipCalculation>>()

        // NB: Simple example that will only emit this single value once
        liveData.value = savedTips.values.toList()
        return liveData

    }

}