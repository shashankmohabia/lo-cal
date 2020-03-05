package com.example.lo_cal.UI.Result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultViewModelFactory(
    private val firstName: String,
    private val secondName: String,
    private val result: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ResultViewModel::class.java)){
            return ResultViewModel(firstName, secondName, result) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}