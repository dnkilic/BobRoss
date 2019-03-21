package com.example.bobross.repository.local

import com.example.bobross.repository.local.db.PostsDatabase
import com.example.bobross.repository.model.Post
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val postsDatabase: PostsDatabase) {
    suspend fun getPosts() = postsDatabase.postDao().getPosts()
    suspend fun savePosts(posts: List<Post>) = postsDatabase.postDao().insertPosts(posts)
    suspend fun getPostById(id: String) = postsDatabase.postDao().getPost(id)
    suspend fun isFavourited(id: String) = postsDatabase.postDao().isFavourited(id)
    suspend fun favorite(post: Post) = postsDatabase.postDao().favorite(
        post.apply {
            if (likedByUser) {
                likedByUser = false
                likes -= 1
            } else {
                likedByUser = true
                likes += 1
            }
        }
    )
}