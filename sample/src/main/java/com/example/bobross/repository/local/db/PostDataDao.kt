package com.example.bobross.repository.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.bobross.repository.model.Post

@Dao
interface PostDataDao {

    @Insert(onConflict = REPLACE)
    fun insertPost(post: Post): Long

    @Insert(onConflict = REPLACE)
    fun insertPosts(posts: List<Post>)

    @Query("SELECT * FROM post WHERE id == :id")
    fun getPost(id: String): Post

    @Query("SELECT * FROM post")
    fun getPosts(): List<Post>

    @Delete
    fun deletePost(post: Post)

    @Query("DELETE FROM post WHERE id == :id")
    fun deletePostById(id: String)
}
