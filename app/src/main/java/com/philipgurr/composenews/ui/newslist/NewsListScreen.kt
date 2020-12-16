package com.philipgurr.composenews.ui.newslist

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.philipgurr.composenews.data.NewsRepository
import com.philipgurr.composenews.domain.NewsPost
import com.philipgurr.composenews.ui.common.DefaultTopBar
import com.philipgurr.composenews.ui.common.Drawer
import com.philipgurr.composenews.ui.common.NewsList
import com.philipgurr.composenews.viewmodel.Screen

@Composable
fun NewsListScreen(repository: NewsRepository, navigate: (Screen) -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val posts by repository.loadNewsPosts().collectAsState(listOf())

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DefaultTopBar(scaffoldState = scaffoldState, "News")
        },
        drawerContent = {
            Drawer(Screen.NewsList, navigate)
        },
        bodyContent = {
            NewsList(it, posts, navigate)
        }
    )
}

val testNews = listOf(
    NewsPost(
        0,
        "Philip Gurr",
        "Trump elected again",
        "Small description",
        "",
        "https://static.politico.com/7a/03/4ff6924c41dfaaa873b00d0dcc94/200323-mike-bloomberg-gty-773.jpg",
        "Big text content"
    ),
    NewsPost(
        0,
        "Philip Gurr",
        "Trump elected again",
        "Small description",
        "",
        "https://static.politico.com/7a/03/4ff6924c41dfaaa873b00d0dcc94/200323-mike-bloomberg-gty-773.jpg",
        "Big text content"
    ),
    NewsPost(
        0,
        "Philip Gurr",
        "Trump elected again",
        "Small description",
        "",
        "https://static.politico.com/7a/03/4ff6924c41dfaaa873b00d0dcc94/200323-mike-bloomberg-gty-773.jpg",
        "Big text content"
    ),
)

@Composable
fun NewsListPreview() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "News") }
            )
        },
        bodyContent = {
            NewsList(it, posts = testNews, navigate = {})
        }
    )
}