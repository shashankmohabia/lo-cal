package com.example.lo_cal.utils.extensions

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.lo_cal.data.models.LoCalEntry

@BindingAdapter("itemId")
fun TextView.setItemIdFormatted(item: LoCalEntry) {
    //do any formatting required
    text = item.id.toString()
}