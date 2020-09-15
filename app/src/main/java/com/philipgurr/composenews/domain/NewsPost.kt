package com.philipgurr.composenews.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsPost(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val content: String?
)