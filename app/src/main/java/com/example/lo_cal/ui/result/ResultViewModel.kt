package com.example.lo_cal.ui.result

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

class ResultViewModel(val args: ResultFragmentArgs, val application: Application) : ViewModel() {

    private val _calculateAgain = MutableLiveData<Boolean>()
    val calculateAgain: LiveData<Boolean>
        get() = _calculateAgain

    private var viewModelJob = Job()

    init {
        _calculateAgain.value = false
    }

    fun onCalculateAgain() {
        _calculateAgain.value = true
    }

    fun onCalculateAgainComplete() {
        _calculateAgain.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}