package com.philipgurr.composenews.ui.favorites

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.philipgurr.composenews.data.NewsRepository
import com.philipgurr.composenews.ui.common.DefaultTopBar
import com.philipgurr.composenews.ui.common.Drawer
import com.philipgurr.composenews.ui.common.NewsList
import com.philipgurr.composenews.domain.Screen

@Composable
fun FavoritesListScreen(repository: NewsRepository, navigate: (Screen) -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val posts = repository.loadFavorites().collectAsState(initial = listOf())

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DefaultTopBar(scaffoldState = scaffoldState, "Favorites")
        },
        drawerContent = {
            Drawer(Screen.FavoritesList, navigate = navigate)
        },
        bodyContent = {
            NewsList(it, posts.value, navigate)
        }
    )
}