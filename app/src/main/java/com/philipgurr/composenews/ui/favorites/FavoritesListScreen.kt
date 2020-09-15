package com.philipgurr.composenews.ui.favorites

import androidx.compose.foundation.Text
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.philipgurr.composenews.data.NewsRepository
import com.philipgurr.composenews.ui.common.DefaultTopBar
import com.philipgurr.composenews.ui.common.Drawer
import com.philipgurr.composenews.ui.common.NewsList
import com.philipgurr.composenews.viewmodel.NavigationViewModel
import com.philipgurr.composenews.viewmodel.Screen

@Composable
fun FavoritesListScreen(navigationViewModel: NavigationViewModel, repository: NewsRepository, navigate: (Screen) -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val posts = repository.loadFavorites().collectAsState(initial = listOf())

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DefaultTopBar(scaffoldState = scaffoldState)
        },
        drawerContent = {
            Drawer(navigationViewModel = navigationViewModel, navigate = navigate)
        },
        bodyContent = {
            NewsList(it, posts.value, navigate)
        }
    )
}