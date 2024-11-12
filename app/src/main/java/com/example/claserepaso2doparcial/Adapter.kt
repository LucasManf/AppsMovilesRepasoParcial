package com.example.claserepaso2doparcial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val quakes: List<Terremoto>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    lateinit var onItemClickListener: (Terremoto) -> Unit

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.textViewTitle)     //es el tv del item list


        fun bind(feature: Terremoto) {
            //title.text = feature.properties.mag.toString()
            title.text = feature.properties.title

            view.setOnClickListener {
                onItemClickListener(feature)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return quakes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quake = quakes[position]
        holder.bind(quake)
    }
}