package com.example.lo_cal.ui.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.lo_cal.data.ResultDatabase
import com.example.lo_cal.data.models.LoCalEntry
import kotlinx.coroutines.*

class DatabaseViewModel(application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val database = ResultDatabase.getInstance(application)

    val entryList = database.dao.getAllEntries()

    private val _onClickShare = MutableLiveData<LoCalEntry>()
    val onClickShare: LiveData<LoCalEntry>
        get() = _onClickShare

    init {
        _onClickShare.value = null
    }

    fun onClickShareDetails(loCalEntry: LoCalEntry) {
        _onClickShare.value = loCalEntry
    }

    fun onSharingComplete() {
        _onClickShare.value = null
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