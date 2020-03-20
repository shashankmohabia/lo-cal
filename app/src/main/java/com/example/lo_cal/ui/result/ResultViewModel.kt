package com.example.lo_cal.ui.result

import android.app.Application
import androidx.lifecycle.*
import com.example.lo_cal.data.ResultDatabase
import com.example.lo_cal.data.models.LoCalEntry
import kotlinx.coroutines.*

class ResultViewModel(args: ResultFragmentArgs, application: Application) :
    AndroidViewModel(application) {

    private val _calculateAgain = MutableLiveData<Boolean>()
    val calculateAgain: LiveData<Boolean>
        get() = _calculateAgain

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val database = ResultDatabase.getInstance(application)

    private val entryList = database.dao.getAllEntries()
    val entriesString = Transformations.map(entryList) {
        it.toString()
    }

    var currentEntry: LoCalEntry

    init {
        _calculateAgain.value = false
        currentEntry = LoCalEntry(
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

    fun cleanDB() {
        uiScope.launch {
            clean()
        }
    }

    private suspend fun clean() {
        withContext(Dispatchers.IO) {
            database.dao.clear()
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