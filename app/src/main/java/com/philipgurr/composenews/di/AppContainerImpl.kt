package com.philipgurr.composenews.di

import android.content.Context
import com.philipgurr.composenews.data.NewsRepository
import com.philipgurr.composenews.data.datasource.NewsDataSource
import com.philipgurr.composenews.data.impl.NewsRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AppContainerImpl(context: Context) : AppContainer {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @ExperimentalCoroutinesApi
    override val newsPostsRepository: NewsRepository by lazy {
        val dataSource = retrofit.create(NewsDataSource::class.java)
        NewsRepositoryImpl(dataSource)
    }
}