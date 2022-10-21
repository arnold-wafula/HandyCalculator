package com.lemonboy.handycalculator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lemonboy.handycalculator.R

class ConverterAdapter(
    private val icons: List<Int>, private val titles: List<String>
): RecyclerView.Adapter<ConverterAdapter.ViewHolder>() {
    private var onItemClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.converter_card_item, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.icon.setImageResource(icons[position])
        holder.title.text = titles[position]
    }

    override fun getItemCount(): Int = titles.size

    fun setOnItemClickListener(itemClickListener: View.OnClickListener) {
        onItemClickListener = itemClickListener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.conv_icon)
        val title: TextView = itemView.findViewById(R.id.conv_title)

        init {
            itemView.tag = this
            itemView.setOnClickListener {

            }
        }
    }
}