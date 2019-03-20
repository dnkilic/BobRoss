package com.example.bobross.repository.remote

import com.dnkilic.bobross.BobRoss
import com.dnkilic.bobross.content.OnContentLoad
import com.example.bobross.repository.model.Post
import com.example.bobross.repository.remote.retrofit.PostService
import kotlinx.coroutines.Deferred
import retrofit2.Response
import javax.inject.Inject

private const val URL = "http://pastebin.com/raw/wgkJgazE"

class RemoteDataSource @Inject constructor(private val postService: PostService) {
    fun getPostsAsync(): Deferred<Response<List<Post>>> = postService.getPostList()
    fun getPosts(onContentLoad: OnContentLoad) = BobRoss.content().request(URL,  onContentLoad)
}