package com.example.bobross.repository.remote.retrofit

import com.example.bobross.repository.model.Post
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface PostService {

    @GET("raw/wgkJgazE")
    fun getPostList(): Deferred<Response<List<Post>>>
}