package com.example.bobross.repository.local

import com.example.bobross.repository.local.db.PostsDatabase
import com.example.bobross.repository.model.Post
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val postsDatabase: PostsDatabase) {

    fun getPosts(): List<Post> {
        return emptyList()
    }

    fun savePosts(posts: List<Post>) {
        // TODO save data via Room
    }
}