package com.philipgurr.composenews.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.philipgurr.composenews.di.AppContainer
import com.philipgurr.composenews.ui.detail.NewsDetailScreen
import com.philipgurr.composenews.ui.favorites.FavoritesListScreen
import com.philipgurr.composenews.ui.newslist.NewsListScreen
import com.philipgurr.composenews.viewmodel.NavigationViewModel
import com.philipgurr.composenews.viewmodel.Screen

@Composable
fun NewsApp(navigationViewModel: NavigationViewModel, appContainer: AppContainer) {
    val currentScreen by navigationViewModel.currentScreen.observeAsState()

    when(currentScreen) {
        is Screen.NewsDetail -> NewsDetailScreen(navigationViewModel, appContainer.newsRepository, (currentScreen as Screen.NewsDetail).newsPost)
        is Screen.FavoritesList -> FavoritesListScreen(navigationViewModel, appContainer.newsRepository) {
            navigationViewModel.navigateTo(it)
        }
        else -> {
            NewsListScreen(navigationViewModel, appContainer.newsRepository) {
                navigationViewModel.navigateTo(it)
            }
        }
    }
}