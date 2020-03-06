package com.example.lo_cal.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel(val firstName: String, val secondName: String, val result: String) : ViewModel() {

    private val _calculateAgain = MutableLiveData<Boolean>()
    val calculateAgain: LiveData<Boolean>
        get() = _calculateAgain

    init {
        _calculateAgain.value = false
    }

    fun onCalculateAgain() {
        _calculateAgain.value = true
    }

    fun onCalculateAgainComplete() {
        _calculateAgain.value = false
    }


}