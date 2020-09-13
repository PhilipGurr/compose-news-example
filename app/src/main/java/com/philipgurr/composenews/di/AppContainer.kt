package com.philipgurr.composenews.di

import com.philipgurr.composenews.data.NewsRepository

interface AppContainer {
    val newsPostsRepository: NewsRepository
}