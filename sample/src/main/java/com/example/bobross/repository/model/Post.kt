package com.example.bobross.repository.model

import com.google.gson.annotations.SerializedName

data class Post(
    val categories: List<Category>,
    val color: String,
    @SerializedName("created_at") val createdAt: String,
    val height: Int,
    val id: String,
    @SerializedName("liked_by_user") val likedByUser: Boolean,
    val likes: Int,
    val links: Links,
    val urls: Urls,
    val user: User,
    val width: Int
)