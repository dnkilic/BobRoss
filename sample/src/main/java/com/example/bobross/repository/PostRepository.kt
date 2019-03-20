package com.example.bobross.repository

import com.dnkilic.bobross.content.OnContentLoad
import com.example.bobross.repository.local.LocalDataSource
import com.example.bobross.repository.model.Post
import com.example.bobross.repository.remote.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(private val localDataSource: LocalDataSource,
                                         private val remoteDataSource: RemoteDataSource) {
    fun getPostsFromViaRetrofit() = remoteDataSource.getPostsAsync() // Not used by purpose. Please see Readme file.
    fun getPostsFromAPI(onContentLoad: OnContentLoad) = remoteDataSource.getPosts(onContentLoad)
    suspend fun getPostsFromDB() = localDataSource.getPosts()
    suspend fun savePosts(posts: List<Post>) = localDataSource.savePosts(posts)
}