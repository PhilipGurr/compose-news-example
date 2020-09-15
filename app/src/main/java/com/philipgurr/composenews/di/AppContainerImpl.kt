package com.philipgurr.composenews.di

import android.content.Context
import androidx.room.Room
import com.philipgurr.composenews.data.NewsRepository
import com.philipgurr.composenews.data.datasource.NewsDataSource
import com.philipgurr.composenews.data.datasource.NewsDatabase
import com.philipgurr.composenews.data.impl.NewsRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val DATABASE_NAME = "news"

class AppContainerImpl(private val context: Context) : AppContainer {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    private val database by lazy {
        Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @ExperimentalCoroutinesApi
    override val newsRepository: NewsRepository by lazy {
        val dataSource = retrofit.create(NewsDataSource::class.java)
        NewsRepositoryImpl(dataSource, database.localNewsDao())
    }
}