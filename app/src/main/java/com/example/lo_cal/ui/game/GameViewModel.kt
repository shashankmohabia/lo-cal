package com.example.lo_cal.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _result = MutableLiveData<Int>()
    val result: LiveData<Int>
        get() = _result

    private val _isInputValid = MutableLiveData<Boolean>()
    val isInputValid: LiveData<Boolean>
        get() = _isInputValid

    init {
        _result.value = 0
        _isInputValid.value = null
    }

    private fun getResult() {
        _result.value = (1..100).random()
    }

    fun calculate(firstName: String, secondName: String) {
        if (firstName == "" || secondName == "") {
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