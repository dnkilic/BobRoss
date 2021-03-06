package com.example.bobross.repository.remote

import com.dnkilic.bobross.BobRoss
import com.dnkilic.bobross.content.OnContentLoad
import com.example.bobross.repository.model.Post
import com.example.bobross.repository.remote.retrofit.PostService
import kotlinx.coroutines.Deferred
import retrofit2.Response
import javax.inject.Inject

private const val POST_URL = "http://pastebin.com/raw/wgkJgazE"
private const val NOTE_URL = "https://www.w3schools.com/xml/note.xml"

class RemoteDataSource @Inject constructor(private val postService: PostService) {
    fun getPostsAsync(): Deferred<Response<List<Post>>> = postService.getPostList()
    fun getPosts(onContentLoad: OnContentLoad) = BobRoss.content().request(POST_URL,  onContentLoad)
    fun getNote(onContentLoad: OnContentLoad) = BobRoss.content().request(NOTE_URL,  onContentLoad)
}