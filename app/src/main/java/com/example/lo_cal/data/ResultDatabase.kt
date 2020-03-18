package com.example.lo_cal.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lo_cal.data.models.LoCalEntry
import com.example.lo_cal.data.Database as DB

@Database(entities = [LoCalEntry::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract val dao: DBDao

    companion object {
        private var INSTANCE: DB? = null

        fun getInstance(context: Context): DB {
            synchronized(this) {
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        com.example.lo_cal.data.Database::class,
                        

                    )
                }
                return instance
            }
        }
    }
}