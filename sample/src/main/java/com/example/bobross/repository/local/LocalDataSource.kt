package com.example.bobross.repository.local

import com.example.bobross.repository.local.db.PostsDatabase
import com.example.bobross.repository.model.Post
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val postsDatabase: PostsDatabase) {
    suspend fun getPosts() = postsDatabase.postDao().getPosts()
    suspend fun savePosts(posts: List<Post>) = postsDatabase.postDao().insertPosts(posts)
}