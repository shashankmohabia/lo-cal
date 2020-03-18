package com.example.lo_cal.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lo_cal.data.models.LoCalEntry
import com.example.lo_cal.utils.constants.DATABASE_TABLE_NAME

@Dao
interface DBDao {

    @Insert
    fun insert(entry: LoCalEntry)

    @Update
    fun update(entry: LoCalEntry)

    @Query("SELECT * FROM $DATABASE_TABLE_NAME WHERE id = :key")
    fun get(key: String)

    @Query("DELETE FROM $DATABASE_TABLE_NAME")
    fun clear()

    @Query("SELECT * FROM $DATABASE_TABLE_NAME ORDER BY id DESC LIMIT 1")
    fun getLatestEntry(): LoCalEntry?

    @Query("SELECT * FROM $DATABASE_TABLE_NAME ORDER BY id DESC")
    fun getAllEntries(): LiveData<List<LoCalEntry>>
}