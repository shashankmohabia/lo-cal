package com.example.lo_cal.UI.Result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel(firstName: String, secondName: String, result: String) : ViewModel() {

    val firstName = firstName
    val secondName = secondName
    val result = result

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