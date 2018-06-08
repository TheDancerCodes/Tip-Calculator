package com.thedancercodes.tipcalculator.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import com.thedancercodes.tipcalculator.R


class SaveDialogFragment : DialogFragment() {

    // Defines the single onSaveTip() function: that takes the name of the tip location
    interface Callback {
        fun onSaveTip(name: String)
    }

    // Nullable type because we don't have access to this until we are attached to the Activity
    private var saveTipCallback: SaveDialogFragment.Callback? = null

    // Assign the saveTipCallback instance based on whether the Context is an
    // instance of SaveDialogFragment.Callback
    //
    // In other Words: Has the Activity implemented this interface?
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        saveTipCallback = context as? Callback

    }

    override fun onDetach() {
        super.onDetach()
        saveTipCallback = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val saveDialog = context?.let { ctx ->

            val editText = EditText(ctx)
            editText.id = viewId
            editText.hint = getString(R.string.save_hint)

            AlertDialog.Builder(ctx)
                    .setView(editText)
                    .setNegativeButton(R.string.action_cancel, null)
                    .setPositiveButton(R.string.action_save, {_,_ -> onSave(editText)})
                    .create()
        }

        return saveDialog!!
    }

    // Private function that fires when the user clicks on the Save Button
    private fun onSave(editText: EditText) {

        // Extract text out of EditText field
        val text = editText.text.toString()

        // If it is not empty, we will invoke another function on the saveTipCallback.
        if (text.isNotEmpty()) {
            saveTipCallback?.onSaveTip(text)
        }

    }


    companion object {
        val viewId = View.generateViewId()
    }
}