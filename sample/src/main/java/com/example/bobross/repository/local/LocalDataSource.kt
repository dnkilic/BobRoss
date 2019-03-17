package com.example.bobross.repository.local

import com.example.bobross.repository.model.Post

class LocalDataSource {

    fun getPosts(): List<Post> {
        return emptyList()
    }

    fun savePosts(posts: List<Post>) {
        // TODO save data via Room
    }
}