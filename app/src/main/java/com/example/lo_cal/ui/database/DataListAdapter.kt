package com.example.lo_cal.ui.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lo_cal.R
import com.example.lo_cal.data.models.LoCalEntry
import com.example.lo_cal.databinding.DatalistItemGridViewBinding

class DataListAdapter(private val clickListener: ItemClickListener) :
    ListAdapter<LoCalEntry, DataListViewHolder>(DataListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolder {
        return DataListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DataListViewHolder, position: Int) {
        val dataItem = getItem(position)
        holder.bind(dataItem, clickListener)
    }
}

class DataListDiffCallback() : DiffUtil.ItemCallback<LoCalEntry>() {
    override fun areItemsTheSame(oldItem: LoCalEntry, newItem: LoCalEntry): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LoCalEntry, newItem: LoCalEntry): Boolean {
        return oldItem == newItem
    }
}

class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun from(parent: ViewGroup): HeaderViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.recyclerview_header, parent, false)
            return HeaderViewHolder(view)
        }
    }
}

class DataListViewHolder private constructor(private val binding: DatalistItemGridViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): DataListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = DatalistItemGridViewBinding.inflate(layoutInflater)
            return DataListViewHolder(binding)
        }
    }

    fun bind(
        dataItem: LoCalEntry,
        clickListener: ItemClickListener
    ) {
        binding.localEntry = dataItem
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}

class ItemClickListener(val clickListener: (item: LoCalEntry) -> Unit) {
    fun onClick(item: LoCalEntry) = clickListener(item)
}

sealed class DataItem {
    abstract val id: Long

    data class LoCalItem(val loCalEntry: LoCalEntry) : DataItem() {
        override val id = loCalEntry.id
    }

    object Header : DataItem() {
        override val id = Long.MIN_VALUE
    }
}