package com.philipgurr.composenews.data.datasource

import com.philipgurr.composenews.domain.NewsPost

data class NewsPostsResponse(
    val status: String,
    val totalResults: Long,
    val articles: List<NewsPost>
)