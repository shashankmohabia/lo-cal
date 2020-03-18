package com.example.lo_cal

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.lo_cal.data.DBDao
import com.example.lo_cal.data.ResultDatabase
import com.example.lo_cal.data.models.LoCalEntry
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class ResultDatabaseTest {

    private lateinit var dao: DBDao
    private lateinit var resultDatabase: ResultDatabase

    @Before
    fun createDB() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        resultDatabase = Room.inMemoryDatabaseBuilder(context, ResultDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = resultDatabase.dao
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        resultDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetData() {
        val entry = LoCalEntry(
            id = "shashank_satya",
            firstName = "shashank",
            secondName = "satya",
            result = 100
        )
        dao.insert(entry)
        val latestEntry = dao.getLatestEntry()
        Assert.assertEquals(entry.firstName, latestEntry?.firstName)


    }
}