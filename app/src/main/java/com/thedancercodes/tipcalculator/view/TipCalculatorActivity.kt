package com.thedancercodes.tipcalculator.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.thedancercodes.tipcalculator.R
import com.thedancercodes.tipcalculator.databinding.ActivityTipCalculatorBinding
import com.thedancercodes.tipcalculator.viewmodel.CalculatorViewModel


class TipCalculatorActivity : AppCompatActivity(), SaveDialogFragment.Callback, LoadDialogFragment.Callback {

    /**
     * Wire ViewModel to View layout in the Activity’s onCreate function & verify that the
     * FAB is wired up correctly.
     *
     * Wire up the variable that the View has declared.
     */

    // DataBinding Variable
    lateinit var binding: ActivityTipCalculatorBinding

    // Snack bar to show user that we have saved tip under the name they entered
    override fun onSaveTip(name: String) {
        // Invoke saveCurrentTip on the VM passing the name along
        binding.vm?.saveCurrentTip(name)

        Snackbar.make(binding.root, "Saved $name", Snackbar.LENGTH_SHORT).show()
    }

    override fun onTipSelected(name: String) {
        // Ask the ViewModel to load the tip by this name
        Snackbar.make(binding.root, "Loaded $name", Snackbar.LENGTH_SHORT).show()
    }

    // Create a clickable menu item on the upper right corner for our users
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tip_calcukator, menu)
        return true
    }

    // Responding to the user clicking on a menu item
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Switch statement: If itemId of the item we just saved matches the action_save id,
        // we display the SaveDialog
        return when(item.itemId) {
            R.id.action_save -> {
                showSaveDialog()
                true
            }
            R.id.action_load -> {
                showLoadDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showLoadDialog() {
        val loadFragment = LoadDialogFragment()
        loadFragment.show(supportFragmentManager, "LoadDialog")
    }

    // showSaveDialog creates a new instance of our SaveDialogFragment & ask it to show itself.
    private fun showSaveDialog() {
        val saveFragment = SaveDialogFragment()
        saveFragment.show(supportFragmentManager, "SaveDialog")
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG, "onCreate")

        super.onCreate(savedInstanceState)

        // Replace Android setContentView with a special DataBinding version which associates
        // and inflates the layout, creates the binding & returns it all in one shot.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tip_calculator)


        // Set the `vm` variable to a new CalculatorViewModel instance
        // This is the magic that creates a new instance of the ViewModel when the app is first
        // launched & then returns the same instance between screen rotations.
        binding.vm = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)


        setSupportActionBar(binding.toolbar)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }
}

const val TAG = "TipCalculatorActivity"
