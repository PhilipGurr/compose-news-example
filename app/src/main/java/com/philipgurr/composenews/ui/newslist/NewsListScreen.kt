package com.philipgurr.composenews.ui.newslist

import androidx.compose.foundation.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.ui.tooling.preview.Preview
import com.philipgurr.composenews.data.NewsRepository
import com.philipgurr.composenews.domain.NewsPost
import com.philipgurr.composenews.ui.common.DefaultTopBar
import com.philipgurr.composenews.ui.common.Drawer
import com.philipgurr.composenews.ui.common.NewsList
import com.philipgurr.composenews.viewmodel.Screen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun NewsListScreen(repository: NewsRepository, navigate: (Screen) -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val posts by repository.loadNewsPosts().collectAsState(listOf())

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DefaultTopBar(scaffoldState = scaffoldState)
        },
        drawerContent = {
            Drawer(navigate = navigate)
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

@Preview(showBackground = true)
@Composable
fun NewsListPreview() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "News") }
            )
        },
        bodyContent = {
            NewsList(it = it, posts = testNews, navigate = {})
        }
    )
}