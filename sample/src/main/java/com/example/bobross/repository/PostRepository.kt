package com.example.bobross.repository

import com.example.bobross.repository.local.LocalDataSource
import com.example.bobross.repository.model.Post
import com.example.bobross.repository.remote.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource) {

    fun getPostsFromAPI() = remoteDataSource.getPostsAsync()
    suspend fun getPostsFromDB() = localDataSource.getPosts()
    suspend fun savePosts(posts: List<Post>) = localDataSource.savePosts(posts)
}