package com.philipgurr.composenews.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.philipgurr.composenews.di.AppContainer
import com.philipgurr.composenews.ui.detail.NewsDetailScreen
import com.philipgurr.composenews.ui.newslist.NewsListScreen
import com.philipgurr.composenews.viewmodel.NavigationViewModel
import com.philipgurr.composenews.viewmodel.Screen

@Composable
fun NewsApp(navigationViewModel: NavigationViewModel, appContainer: AppContainer) {
    val currentScreen by navigationViewModel.currentScreen.observeAsState()

    when(currentScreen) {
        is Screen.NewsDetail -> NewsDetailScreen((currentScreen as Screen.NewsDetail).newsPost)
        else -> {
            println("ab")
            NewsListScreen(repository = appContainer.newsPostsRepository) {
                navigationViewModel.navigateTo(it)
            }
        }
    }
}