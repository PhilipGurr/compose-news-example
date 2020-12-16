package com.philipgurr.composenews.domain

sealed class Screen(val navUrl: String) {
    object NewsList : Screen("newsList")
    object FavoritesList : Screen("favoritesList")
    data class NewsDetail(val newsPost: NewsPost) : Screen("detail/{post}")
    object Previous : Screen("")
}