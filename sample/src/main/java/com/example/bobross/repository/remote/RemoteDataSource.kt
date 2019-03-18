package com.example.bobross.repository.remote

import com.example.bobross.repository.model.Post
import com.example.bobross.repository.remote.retrofit.PostService

class RemoteDataSource(private val postService: PostService) {

    fun getPosts(): List<Post> {
        return emptyList()
    }
}