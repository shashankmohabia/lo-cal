package com.example.lo_cal.ui.result

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultViewModelFactory(
    private val args: ResultFragmentArgs,
    private val application: Application
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(
                args,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}