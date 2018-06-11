package com.thedancercodes.tipcalculator.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.thedancercodes.tipcalculator.R
import com.thedancercodes.tipcalculator.databinding.SavedTipCalculationsListItemBinding
import com.thedancercodes.tipcalculator.viewmodel.TipCalculationSummaryItem


class TipSummaryAdapter(val onItemSelected: (item: TipCalculationSummaryItem) -> Unit)
    : RecyclerView.Adapter<TipSummaryAdapter.TipSummaryViewHolder>() {


    // Member variable storing the list of items
    private val tipCalculationSummaries = mutableListOf<TipCalculationSummaryItem>()

    // Function to replace current list
    fun updateList(updates: List<TipCalculationSummaryItem>) {
        tipCalculationSummaries.clear()
        tipCalculationSummaries.addAll(updates)
        notifyDataSetChanged() // Notify Recyclerview that this data set has changed

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipSummaryViewHolder {

        // Inflate the View responsible for rendering each item & return it wrapped in a VH
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<SavedTipCalculationsListItemBinding>(
                inflater, R.layout.saved_tip_calculations_list_item, parent, false)

        return TipSummaryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        // Size of the list
        return tipCalculationSummaries.size
    }

    override fun onBindViewHolder(holder: TipSummaryViewHolder, position: Int) {
        holder.bind(tipCalculationSummaries[position])
    }


    // RootView of item to be displayed is passed into the constructor
    inner class TipSummaryViewHolder(val binding: SavedTipCalculationsListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        // Main job of VH is to bind tip calculation summary items to the View at the right time
        // when asked to do so by the adapter.
        fun bind(item: TipCalculationSummaryItem) {
            binding.item = item

            // When the click event happens, invoke this function passing this item that was clicked
            binding.root.setOnClickListener { onItemSelected(item) }
            binding.executePendingBindings() // Update the View right away
        }
    }

}