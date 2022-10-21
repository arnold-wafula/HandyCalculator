package com.lemonboy.handycalculator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lemonboy.handycalculator.R
import com.lemonboy.handycalculator.db.HistoryItem
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter: ListAdapter<HistoryItem, HistoryAdapter.ViewHolder>(HistoryComparator()) {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val input: TextView = itemView.findViewById(R.id.calcInput)
        val output: TextView = itemView.findViewById(R.id.calcOutput)
        val timeStamp: TextView = itemView.findViewById(R.id.timeStamp)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.input.text = item.inputString
        holder.output.text = item.outputString
        holder.timeStamp.text = getFormattedDate(item.calculatedOn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.history_card_item, parent, false)
        return ViewHolder(view)
    }

    class HistoryComparator: DiffUtil.ItemCallback<HistoryItem>() {
        override fun areItemsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean {
            return oldItem == newItem
        }
    }

    private fun getFormattedDate(calculatedOn: Date?): String {
        var time = "Calculated at: "
        time += calculatedOn?.let {
            val sdf = SimpleDateFormat("HH:mm d MMM, yyyy", Locale.getDefault())
            sdf.format(calculatedOn)
        } ?: "Not Found"
        return time
    }
}

/*
    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var pos: Int = 0
        private val tvInput: TextView = itemView.findViewById(R.id.calcInput)
        private val tvOutput: TextView = itemView.findViewById(R.id.calcOutput)
        private val tvTimeStamp: TextView = itemView.findViewById(R.id.timeStamp)

        fun setData(input: String, output: String, calculatedOn: Date?, position: Int) {
            tvInput.text = input
            tvOutput.text = output
            tvTimeStamp.text = getFormattedDate(calculatedOn)
            this.pos = position
        }

        fun setListeners() {
            itemView.setOnClickListener {}

            /**
            ivRowDelete.setOnClickListener {
                onDeleteClickListener.onDeleteClickListener(historyList[pos])
            }*/
        }

        private fun getFormattedDate(calculatedOn: Date?): String {
            var time = "Calculated at: "
            time += calculatedOn?.let {
                val sdf = SimpleDateFormat("HH:mm d MMM, yyyy", Locale.getDefault())
                sdf.format(calculatedOn)
            } ?: "Not Found"
            return time
        }
    }*/