package com.philipgurr.composenews.data.impl

import com.philipgurr.composenews.data.NewsRepository
import com.philipgurr.composenews.data.datasource.NewsDataSource
import com.philipgurr.composenews.domain.NewsPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
class NewsRepositoryImpl(
    private val newsDataSource: NewsDataSource
) : NewsRepository {
    private val posts = MutableStateFlow<List<NewsPost>>(listOf())

    override fun observeNewsPosts(): Flow<List<NewsPost>> = posts

    override suspend fun loadNewsPosts() = withContext(Dispatchers.IO) {
        try {
            posts.value = newsDataSource.getNewsPosts("us").articles
        } catch(ex: Exception) {
            posts.value = listOf()
        }
    }
}