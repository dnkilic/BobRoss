package com.example.bobross.repository.model

import androidx.room.*
import com.squareup.moshi.Json

@Entity(tableName = "post")
data class Post(
    @ColumnInfo var categories: List<Category>,
    @ColumnInfo var color: String,
    @ColumnInfo @field:Json(name = "created_at") var createdAt: String,
    @ColumnInfo var height: Int,
    @ColumnInfo var width: Int,
    @PrimaryKey var id: String,
    @ColumnInfo @field:Json(name = "liked_by_user") var likedByUser: Boolean,
    @ColumnInfo var likes: Int,
    @ColumnInfo var links: Links,
    @ColumnInfo var urls: Urls,
    @ColumnInfo var user: User
)