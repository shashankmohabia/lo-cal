package com.example.lo_cal.ui.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import com.example.lo_cal.data.ResultDatabase
import kotlinx.coroutines.*

class DatabaseViewModel(application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val database = ResultDatabase.getInstance(application)

    private val entryList = database.dao.getAllEntries()
    val entriesString = Transformations.map(entryList) {
        it.toString()
    }

    fun onClean() {
        uiScope.launch {
            clean()
        }
    }

    private suspend fun clean() {
        withContext(Dispatchers.IO) {
            database.dao.clear()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}