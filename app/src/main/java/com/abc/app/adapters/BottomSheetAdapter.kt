package com.abc.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abc.app.R
import com.abc.app.data.Stats

class BottomSheetAdapter(private val statistics: Stats) :
    RecyclerView.Adapter<BottomSheetAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvCount: TextView = itemView.findViewById(R.id.tvCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.bottom_sheet_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val charWithCount = statistics.top3CharsWithCounts[position]
        holder.tvName.text = charWithCount.key.toString()
        holder.tvCount.text = charWithCount.value.toString()
    }

    override fun getItemCount(): Int = statistics.top3CharsWithCounts.size
}