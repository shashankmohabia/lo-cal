package com.example.lo_cal.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    val firstName = MutableLiveData<String>()
    val secondName = MutableLiveData<String>()

    private val _result = MutableLiveData<Int>()
    val result: LiveData<Int>
        get() = _result

    private val _isInputValid = MutableLiveData<Boolean>()
    val isInputValid: LiveData<Boolean>
        get() = _isInputValid

    init {
        _result.value = 0
        _isInputValid.value = null
        firstName.value = ""
        secondName.value = ""
    }

    private fun getResult() {
        _result.value = (1..100).random()
    }

    fun calculate() {
        if (firstName.value == "" || secondName.value == "") {
            _isInputValid.value = false
        } else {
            getResult()
            _isInputValid.value = true
        }
    }

    fun onCalculationComplete() {
        _isInputValid.value = null
    }
}