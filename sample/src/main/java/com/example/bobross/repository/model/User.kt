package com.example.bobross.repository.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: String,
    val links: Links,
    val name: String,
    @SerializedName("profile_image") val profileImage: ProfileImage,
    val username: String
)