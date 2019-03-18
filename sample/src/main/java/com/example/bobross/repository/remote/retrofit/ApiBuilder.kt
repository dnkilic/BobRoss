package com.example.bobross.repository.remote.retrofit

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiBuilder(private val context: Context,
                 private val endpoint: String = "http://pastebin.com/") {

    fun <T> build(clazz: Class<T>): T {
        val builder = OkHttpClient.Builder()
        builder.cache(getCache(context))
        builder.readTimeout(READ_TIME_OUT, TimeUnit.MINUTES)
        builder.writeTimeout(WRITE_TIME_OUT, TimeUnit.MINUTES)
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.MINUTES)
        builder.retryOnConnectionFailure(false)

        val client = builder.build()
        return buildRetrofit(client, clazz)
    }

    private fun <T> buildRetrofit(client: OkHttpClient, clazz: Class<T>): T {
        val retrofit = Retrofit.Builder().baseUrl(endpoint)
                .client(client)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        val output = retrofit.create(clazz)
        return clazz.cast(output)!!
    }

    private fun getCache(context: Context): Cache {
        return Cache(context.cacheDir, CACHE_SIZE)
    }

    companion object {
        private const val WRITE_TIME_OUT = 5L // Minutes
        private const val READ_TIME_OUT = 5L // Minutes
        private const val CONNECT_TIME_OUT = 5L // Minutes
        private const val CACHE_SIZE = 10485760L // 10 MB
    }
}
