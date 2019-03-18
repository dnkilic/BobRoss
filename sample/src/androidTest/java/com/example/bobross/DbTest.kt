package com.example.bobross

import android.content.Context
import org.junit.Rule
import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.bobross.repository.local.db.PostsDatabase
import java.util.concurrent.TimeUnit

abstract class DbTest {

    @Rule
    @JvmField
    val countingTaskExecutorRule = CountingTaskExecutorRule()
    private lateinit var _db: PostsDatabase
    val db: PostsDatabase
        get() = _db

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @org.junit.Before
    fun setUp() {
        _db = Room.inMemoryDatabaseBuilder(
            context,
            PostsDatabase::class.java
        ).build()
    }

    @org.junit.After
    fun tearDown() {
        countingTaskExecutorRule.drainTasks(DURATION_TO_WAIT_SEC, TimeUnit.SECONDS)
        _db.close()
    }

    companion object {
        const val DURATION_TO_WAIT_SEC = 10
    }
}
