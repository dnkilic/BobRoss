package com.example.bobross.repository.model

import com.squareup.moshi.Json

data class User(
    val id: String,
    val links: Links,
    val name: String,
    @field:Json(name = "profile_image") val profileImage: ProfileImage,
    val username: String
)