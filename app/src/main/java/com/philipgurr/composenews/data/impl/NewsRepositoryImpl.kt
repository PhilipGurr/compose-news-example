package com.philipgurr.composenews.data.impl

import android.util.Log
import com.philipgurr.composenews.data.NewsRepository
import com.philipgurr.composenews.data.datasource.LocalNewsDataSource
import com.philipgurr.composenews.data.datasource.NewsDataSource
import com.philipgurr.composenews.domain.NewsPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class NewsRepositoryImpl(
    private val newsDataSource: NewsDataSource,
    private val localNewsDao: LocalNewsDataSource
) : NewsRepository {
    override fun loadNewsPosts() = flow {
        emit(newsDataSource.getNewsPosts("us").articles)
    }.flowOn(Dispatchers.IO)

    override fun loadFavorites(): Flow<List<NewsPost>> {
        return try {
            localNewsDao.getAll()
        } catch (ex: Exception) {
            localNewsDao.getAll()
        }
    }

    override suspend fun addFavorite(newsPost: NewsPost) {
        localNewsDao.insert(newsPost)
    }

    override suspend fun deleteFavorite(newsPost: NewsPost) {
        localNewsDao.delete(newsPost)
    }
}