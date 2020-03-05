package com.example.lo_cal.UI.Game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    val result = MutableLiveData<Int>()

    init {
        result.value = 0
    }

    fun getResult() {
        result.value = (1..100).random()
    }

}