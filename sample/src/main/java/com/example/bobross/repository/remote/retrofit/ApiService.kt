package com.example.bobross.repository.remote.retrofit

import android.content.Context

class ApiService private constructor(private val context: Context) {

    val apiMethods by lazy { createApiMethods() }

    private fun createApiMethods(): PostService {
        val builder = ApiBuilder(context)
        return builder.build(PostService::class.java)
    }

    companion object {
        private var instance: ApiService? = null

        @JvmStatic
        fun getInstance(context: Context) = instance ?: ApiService(context)
    }
}
