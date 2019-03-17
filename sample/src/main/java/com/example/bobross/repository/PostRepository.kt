package com.example.bobross.repository

import com.example.bobross.base.repository.Repository
import com.example.bobross.repository.local.LocalDataSource
import com.example.bobross.repository.model.Post
import com.example.bobross.repository.remote.RemoteDataSource

class PostRepository(private val localDataSource: LocalDataSource,
                     private val remoteDataSource: RemoteDataSource) : Repository<Post>  {

    override fun getList(): List<Post> {
        return emptyList()
    }

}