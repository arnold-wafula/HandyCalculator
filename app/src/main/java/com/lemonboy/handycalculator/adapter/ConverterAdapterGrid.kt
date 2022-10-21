package com.lemonboy.handycalculator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lemonboy.handycalculator.R
import com.lemonboy.handycalculator.ui.currency.CurrencyFragment
import com.lemonboy.handycalculator.ui.energy.EnergyFragment
import com.lemonboy.handycalculator.ui.length.LengthFragment
import com.lemonboy.handycalculator.ui.temperature.TempFragment
import com.lemonboy.handycalculator.ui.volume.VolumeFragment
import com.lemonboy.handycalculator.ui.weight.WeightFragment

class ConverterAdapterGrid(
    private val icons: List<Int>, private val titles: List<String>
): RecyclerView.Adapter<ConverterAdapterGrid.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.conv_icon)
        val title: TextView = itemView.findViewById(R.id.conv_title)

        init {
            itemView.setOnClickListener {

                when(absoluteAdapterPosition) {
                    0 -> WeightFragment()
                    1 -> VolumeFragment()
                    2 -> LengthFragment()
                    3 -> TempFragment()
                    4 -> EnergyFragment()
                    5 -> CurrencyFragment()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.converter_card_item_grid, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.icon.setImageResource(icons[position])
        holder.title.text = titles[position]
    }

    override fun getItemCount(): Int = titles.size
}