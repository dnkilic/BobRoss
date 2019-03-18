package com.example.bobross.repository.local.db

import android.text.TextUtils
import androidx.room.TypeConverter
import com.example.bobross.repository.model.Category
import com.example.bobross.repository.model.Links
import com.example.bobross.repository.model.Urls
import com.example.bobross.repository.model.User
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun fromCategoryList(categories: List<Category>?): String? {
        if (categories == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Category>>() {

        }.type
        return gson.toJson(categories, type)
    }

    @TypeConverter
    fun toCategoryList(categoriesString: String?): List<Category>? {
        if (categoriesString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Category>>() {

        }.type
        return gson.fromJson(categoriesString, type)
    }

    @TypeConverter
    fun stringToUrls(string: String): Urls? {
        if (TextUtils.isEmpty(string))
            return null
        return Gson().fromJson(string, Urls::class.java)
    }

    @TypeConverter
    fun urlsToString(urls: Urls): String {
        return Gson().toJson(urls)
    }

    @TypeConverter
    fun stringToUser(string: String): User? {
        if (TextUtils.isEmpty(string))
            return null
        return Gson().fromJson(string, User::class.java)
    }

    @TypeConverter
    fun userToString(user: User): String {
        return Gson().toJson(user)
    }

    @TypeConverter
    fun stringToLinks(string: String): Links? {
        if (TextUtils.isEmpty(string))
            return null
        return Gson().fromJson(string, Links::class.java)
    }

    @TypeConverter
    fun linksToString(links: Links): String {
        return Gson().toJson(links)
    }
}