package com.thedancercodes.tipcalculator.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import com.android.databinding.library.baseAdapters.BR


abstract class ObservableViewModel(app: Application) : AndroidViewModel(app), Observable {

    // mCallbacks is a PropertyChangeRegistry object that we delegate the registration process to.
    // Lazy meaning PropertyChangeRegistry wont be created until it is first accessed.
    @delegate:Transient
    private val mCallbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        mCallbacks.remove(callback)
    }

    // A way for our ViewModel to tell the View that a property has changed
    fun notifyChange() {

        // Passing 0 means all properties have changed & that view should reevaluate
        // any properties its referencing from this Observable
        mCallbacks.notifyChange(this, BR._all)
    }

}
