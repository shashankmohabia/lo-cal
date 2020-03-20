package com.example.lo_cal.ui.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import com.example.lo_cal.data.ResultDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class DatabaseViewModel(application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val database = ResultDatabase.getInstance(application)

    private val entryList = database.dao.getAllEntries()
    val entriesString = Transformations.map(entryList) {
        it.toString()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}