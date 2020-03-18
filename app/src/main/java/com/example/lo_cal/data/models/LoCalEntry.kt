package com.example.lo_cal.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lo_cal.utils.constants.DATABASE_TABLE_NAME

@Entity(tableName = DATABASE_TABLE_NAME)
data class LoCalEntry(
    @PrimaryKey
    var id: String,

    @ColumnInfo(name = "first_name")
    var firstName: String,

    @ColumnInfo(name = "second_name")
    var secondName: String,

    @ColumnInfo(name = "result")
    var result: Int,

    @ColumnInfo(name = "record_time")
    var record_time: Long = System.currentTimeMillis()
)
