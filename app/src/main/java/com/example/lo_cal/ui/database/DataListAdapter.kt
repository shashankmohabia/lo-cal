package com.example.lo_cal.ui.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lo_cal.R
import com.example.lo_cal.data.models.LoCalEntry

class DataListAdapter : RecyclerView.Adapter<DataListAdapter.DataListViewHolder>() {

    var data = listOf<LoCalEntry>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolder {
        return DataListViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DataListViewHolder, position: Int) {
        val dataItem = data[position]
        holder.bind(dataItem)
    }

    class DataListDiffCallback() : DiffUtil.ItemCallback<LoCalEntry>() {
        override fun areItemsTheSame(oldItem: LoCalEntry, newItem: LoCalEntry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LoCalEntry, newItem: LoCalEntry): Boolean {
            return oldItem == newItem
        }
    }

    class DataListViewHolder private constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        companion object {
            fun from(parent: ViewGroup): DataListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(
                        R.layout.datalist_item,
                        parent, false
                    )
                return DataListViewHolder(view)
            }
        }

        private val id: TextView = itemView.findViewById(R.id.item_id)
        private val firstName: TextView = itemView.findViewById(R.id.item_first_name)
        private val secondName: TextView = itemView.findViewById(R.id.item_second_name)
        private val result: TextView = itemView.findViewById(R.id.item_result)

        fun bind(dataItem: LoCalEntry) {
            id.text = dataItem.id.toString()
            firstName.text = dataItem.firstName
            secondName.text = dataItem.secondName
            result.text = dataItem.result
        }
    }
}