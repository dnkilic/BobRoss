package com.example.bobross.repository.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bobross.repository.model.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PostsDatabase : RoomDatabase() {
    abstract fun postDao(): PostDataDao
}
