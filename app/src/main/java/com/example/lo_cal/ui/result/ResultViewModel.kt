package com.example.lo_cal.ui.result

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lo_cal.data.ResultDatabase
import com.example.lo_cal.data.models.LoCalEntry
import com.example.lo_cal.data.models.getId
import kotlinx.coroutines.*

class ResultViewModel(args: ResultFragmentArgs, application: Application) :
    ViewModel() {

    private val _calculateAgain = MutableLiveData<Boolean>()
    val calculateAgain: LiveData<Boolean>
        get() = _calculateAgain

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val database = ResultDatabase.getInstance(application)

    var currentEntry: LoCalEntry

    init {
        _calculateAgain.value = false
        currentEntry = LoCalEntry(
            id = getId(args.firstPersonName, args.secondPersonName),
            firstName = args.firstPersonName,
            secondName = args.secondPersonName,
            result = args.result
        )

        updateDBWithNewEntry()
    }

    private fun updateDBWithNewEntry() {
        uiScope.launch {
            insertInDB(currentEntry)
        }
    }

    private suspend fun insertInDB(newEntry: LoCalEntry) {
        withContext(Dispatchers.IO) {
            database.dao.insert(newEntry)
        }
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