package com.example.lo_cal.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lo_cal.data.models.LoCalEntry
import com.example.lo_cal.utils.constants.DATABASE_NAME

@Database(entities = [LoCalEntry::class], version = 1, exportSchema = false)
abstract class ResultDatabase : RoomDatabase() {

    abstract val dao: DBDao

    companion object {
        @Volatile
        private var INSTANCE: ResultDatabase? = null

        fun getInstance(context: Context): ResultDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ResultDatabase::class.java,
                        DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}