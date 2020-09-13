package com.philipgurr.composenews.domain

data class NewsPost(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val content: String?
)