package com.example.lo_cal.UI.Result

import androidx.lifecycle.ViewModel

class ResultViewModel(firstName: String, secondName: String, result: String) : ViewModel() {
    val firstName = firstName
    val secondName = secondName
    val result = result
}