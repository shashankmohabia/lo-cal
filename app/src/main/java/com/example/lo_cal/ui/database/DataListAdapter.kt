package com.example.lo_cal.ui.database

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.lo_cal.R
import com.example.lo_cal.data.models.LoCalEntry
import com.example.lo_cal.databinding.DatalistItemGridViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

class DataListAdapter(private val clickListener: ItemClickListener) :
    ListAdapter<DataItem, ViewHolder>(DataListDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> DataListViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    fun addHeaderAndSubmitList(list: List<LoCalEntry>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.LoCalItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is DataListViewHolder -> {
                val dataItem = getItem(position) as DataItem.LoCalItem
                holder.bind(dataItem.loCalEntry, clickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.LoCalItem -> ITEM_VIEW_TYPE_ITEM
        }
    }
}

class DataListDiffCallback() : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

class HeaderViewHolder(view: View) : ViewHolder(view) {
    companion object {
        fun from(parent: ViewGroup): HeaderViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.recyclerview_header, parent, false)
            return HeaderViewHolder(view)
        }
    }
}

class DataListViewHolder private constructor(private val binding: DatalistItemGridViewBinding) :
    ViewHolder(binding.root) {

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