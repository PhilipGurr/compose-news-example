package com.philipgurr.composenews.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.philipgurr.composenews.R
import com.philipgurr.composenews.data.NewsRepository
import com.philipgurr.composenews.domain.NewsPost
import com.philipgurr.composenews.domain.Screen
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.launch

@Composable
fun NewsDetailScreen(
    newsRepository: NewsRepository,
    newsPost: NewsPost,
    navigate: (Screen) -> Unit
) {
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Article") },
                navigationIcon = {
                    IconButton(
                        onClick = { navigate(Screen.Previous) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_back),
                            contentDescription = "Navigate"
                        )
                    }
                },
                actions = {
                    val favorites =
                        newsRepository.loadFavorites().collectAsState(initial = listOf())
                    val isFavorite = favorites.value.any { newsPost.url == it.url }
                    IconButton(
                        onClick = {
                            scope.launch {
                                if (!isFavorite) {
                                    newsRepository.addFavorite(newsPost)
                                }
                            }
                        }) {
                        val res = if (isFavorite) {
                            R.drawable.favorite_filled
                        } else {
                            R.drawable.favorite_empty
                        }
                        Icon(painter = painterResource(id = res), contentDescription = "Favorite")
                    }
                }
            )
        },
        content = {
            Article(newsPost = newsPost)
        }
    )
}

@Composable
fun Article(newsPost: NewsPost) {
    ConstraintLayout {
        val (image, title, articleText) = createRefs()

        val imageUrl = newsPost.urlToImage
        if (imageUrl != null && imageUrl.isNotEmpty()) {
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = "Article Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }


        Text(
            text = newsPost.title ?: "",
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .constrainAs(title) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        Text(
            text = newsPost.content ?: "No content!",
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .constrainAs(articleText) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
    }
}