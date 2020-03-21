package com.example.lo_cal.ui.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lo_cal.R
import com.example.lo_cal.data.models.LoCalEntry

class DataListAdapter : RecyclerView.Adapter<DataListViewHolder>() {

    var data = listOf<LoCalEntry>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(
                R.layout.datalist_item,
                parent, false
            )
        return DataListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DataListViewHolder, position: Int) {
        val dataItem = data[position]
        holder.id.text = dataItem.id.toString()
        holder.firstName.text = dataItem.firstName
        holder.secondName.text = dataItem.secondName
        holder.result.text = dataItem.result
    }
}

class DataListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val id: TextView = itemView.findViewById(R.id.item_id)
    val firstName: TextView = itemView.findViewById(R.id.item_first_name)
    val secondName: TextView = itemView.findViewById(R.id.item_second_name)
    val result: TextView = itemView.findViewById(R.id.item_result)
}