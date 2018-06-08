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

import kotlinx.android.synthetic.main.activity_tip_calculator.*


class TipCalculatorActivity : AppCompatActivity() {

    /**
     * Wire ViewModel to View layout in the Activity’s onCreate function & verify that the
     * FAB is wired up correctly.
     *
     * Wire up the variable that the View has declared.
     */

    // DataBinding Variable
    lateinit var binding: ActivityTipCalculatorBinding

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

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}

const val TAG = "TipCalculatorActivity"
