package com.example.bobross.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.bobross.repository.PostRepository
import com.example.bobross.repository.local.LocalDataSource
import com.example.bobross.repository.local.db.PostsDatabase
import com.example.bobross.repository.remote.RemoteDataSource
import com.example.bobross.repository.remote.retrofit.PostService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val WRITE_TIME_OUT = 5L // Minutes
private const val READ_TIME_OUT = 5L // Minutes
private const val CONNECT_TIME_OUT = 5L // Minutes

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun providesContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providesContentDatabase(context: Context): PostsDatabase = Room.databaseBuilder(context,
            PostsDatabase::class.java, "post.db").build()

    @Singleton
    @Provides
    fun providesLocalDataSource(postsDatabase: PostsDatabase): LocalDataSource =
            LocalDataSource(postsDatabase)

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(READ_TIME_OUT, TimeUnit.MINUTES)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.MINUTES)
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MINUTES)
            .retryOnConnectionFailure(false)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiMethods(okHttpClient: OkHttpClient): PostService {
        return Retrofit.Builder()
            .baseUrl("http://pastebin.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PostService::class.java)
    }

    @Singleton
    @Provides
    fun providesRemoteDataSource(postService: PostService): RemoteDataSource =
        RemoteDataSource(postService)

    @Singleton
    @Provides
    fun provideNewsRepository(localDataSource: LocalDataSource,
                              remoteDataSource: RemoteDataSource): PostRepository =
        PostRepository(localDataSource, remoteDataSource)
}
