package com.philipgurr.composenews.ui.newslist

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.luca992.compose.image.CoilImage
import com.philipgurr.composenews.data.NewsRepository
import com.philipgurr.composenews.domain.NewsPost
import com.philipgurr.composenews.viewmodel.Screen
import kotlinx.coroutines.launch

@Composable
fun NewsListScreen(repository: NewsRepository, navigate: (Screen) -> Unit) {
    val scope = rememberCoroutineScope()
    scope.launch { repository.loadNewsPosts() }

    val posts = repository.observeNewsPosts().collectAsState(initial = listOf())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "News") }
            )
        },
        bodyContent = {
            NewsList(it, posts, navigate)
        }
    )
}

@Composable
private fun NewsList(
    it: InnerPadding,
    posts: State<List<NewsPost>>,
    navigate: (Screen) -> Unit
) {
    val baseModifier = Modifier.padding(it)

    LazyColumnFor(
        items = posts.value,
        modifier = baseModifier.fillMaxWidth().fillMaxHeight()
    ) { newsPost ->
        NewsListItem(newsPost = newsPost, onClick = {
            navigate(Screen.NewsDetail(newsPost))
        })
    }
}

val testNews = listOf(
    NewsPost(
        "Philip Gurr",
        "Trump elected again",
        "Small description",
        "",
        "https://static.politico.com/7a/03/4ff6924c41dfaaa873b00d0dcc94/200323-mike-bloomberg-gty-773.jpg",
        "Big text content"
    ),
    NewsPost(
        "Philip Gurr",
        "Trump elected again",
        "Small description",
        "",
        "https://static.politico.com/7a/03/4ff6924c41dfaaa873b00d0dcc94/200323-mike-bloomberg-gty-773.jpg",
        "Big text content"
    ),
    NewsPost(
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
            val baseModifier = Modifier.padding(it)

            LazyColumnFor(
                items = testNews,
                modifier = baseModifier.fillMaxWidth().fillMaxHeight()
            ) { newsPost ->
                NewsListItem(newsPost = newsPost, onClick = {

                })
            }
        }
    )
}

@Composable
fun NewsListItem(newsPost: NewsPost, onClick: (NewsPost) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().clickable(onClick = { onClick(newsPost) }),
        horizontalGravity = Alignment.CenterHorizontally,
    ) {
        CoilImage(newsPost.urlToImage)

        val paddingTitle = Modifier.padding(5.dp, 10.dp, 5.dp, 5.dp)
        val paddingDesc = Modifier.padding(5.dp, 5.dp, 5.dp, 10.dp)
        Text(text = newsPost.title, style = MaterialTheme.typography.body1, modifier = paddingTitle)
        Text(text = newsPost.description, style = MaterialTheme.typography.body2, modifier = paddingDesc)
    }
}