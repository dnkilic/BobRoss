package com.example.bobross.repository.local.db

import android.text.TextUtils
import androidx.room.TypeConverter
import com.example.bobross.repository.model.Category
import com.example.bobross.repository.model.Links
import com.example.bobross.repository.model.Urls
import com.example.bobross.repository.model.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Converters {

    private val moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @TypeConverter
    fun fromCategoryList(categories: List<Category>?): String? {
        if (categories == null) {
            return null
        }
        val types = Types.newParameterizedType(List::class.java, Category::class.java)
        val adapter = moshi.adapter<List<Category>>(types)
        return adapter.toJson(categories)
    }

    @TypeConverter
    fun toCategoryList(categoriesString: String?): List<Category>? {
        if (categoriesString == null) {
            return null
        }

        val types = Types.newParameterizedType(List::class.java, Category::class.java, String::class.java)
        val adapter = moshi.adapter<List<Category>>(types)
        return adapter.fromJson(categoriesString)
    }

    @TypeConverter
    fun stringToUrls(string: String): Urls? {
        if (TextUtils.isEmpty(string))
            return null

        val types = Types.newParameterizedType(Urls::class.java, String::class.java)
        val adapter = moshi.adapter<Urls>(types)
        return adapter.fromJson(string)
    }

    @TypeConverter
    fun urlsToString(urls: Urls): String {
        val types = Types.newParameterizedType(Urls::class.java, String::class.java)
        val adapter = moshi.adapter<Urls>(types)
        return adapter.toJson(urls)
    }

    @TypeConverter
    fun stringToUser(string: String): User? {
        if (TextUtils.isEmpty(string))
            return null

        val types = Types.newParameterizedType(User::class.java, String::class.java)
        val adapter = moshi.adapter<User>(types)
        return adapter.fromJson(string)
    }

    @TypeConverter
    fun userToString(user: User): String {
        val types = Types.newParameterizedType(User::class.java, String::class.java)
        val adapter = moshi.adapter<User>(types)
        return adapter.toJson(user)
    }

    @TypeConverter
    fun stringToLinks(string: String): Links? {
        if (TextUtils.isEmpty(string))
            return null
        val types = Types.newParameterizedType(Links::class.java, String::class.java)
        val adapter = moshi.adapter<Links>(types)
        return adapter.fromJson(string)
    }

    @TypeConverter
    fun linksToString(links: Links): String {
        val types = Types.newParameterizedType(Links::class.java, String::class.java)
        val adapter = moshi.adapter<Links>(types)
        return adapter.toJson(links)
    }
}