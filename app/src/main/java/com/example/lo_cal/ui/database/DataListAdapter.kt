package com.example.lo_cal.ui.database

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lo_cal.data.models.LoCalEntry
import com.example.lo_cal.databinding.DatalistItemListViewBinding

class DataListAdapter :
    ListAdapter<LoCalEntry, DataListAdapter.DataListViewHolder>(DataListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolder {
        return DataListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DataListViewHolder, position: Int) {
        val dataItem = getItem(position)
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

    class DataListViewHolder private constructor(private val binding: DatalistItemListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): DataListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DatalistItemListViewBinding.inflate(layoutInflater)
                return DataListViewHolder(binding)
            }
        }

        fun bind(dataItem: LoCalEntry) {
            binding.localEntry = dataItem
            binding.executePendingBindings()
        }
    }
}