package com.example.bobross.repository.remote

import com.example.bobross.repository.model.Post
import com.example.bobross.repository.remote.retrofit.PostService
import kotlinx.coroutines.Deferred
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val postService: PostService) {
    fun getPostsAsync(): Deferred<Response<List<Post>>> = postService.getPostList()
}