package com.philipgurr.composenews.data

import com.philipgurr.composenews.domain.NewsPost
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun loadNewsPosts(): Flow<List<NewsPost>>
    fun loadFavorites(): Flow<List<NewsPost>>
    suspend fun addFavorite(newsPost: NewsPost)
    suspend fun deleteFavorite(newsPost: NewsPost)
}