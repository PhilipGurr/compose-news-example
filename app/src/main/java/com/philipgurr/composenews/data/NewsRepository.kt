package com.philipgurr.composenews.data

import com.philipgurr.composenews.domain.NewsPost
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun observeNewsPosts(): Flow<List<NewsPost>>
    suspend fun loadNewsPosts()
}