package com.example.lo_cal.UI.Game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _result = MutableLiveData<Int>()
    val result: LiveData<Int>
        get() = _result

    init {
        _result.value = 0
    }

    fun getResult() {
        _result.value = (1..100).random()
    }

}