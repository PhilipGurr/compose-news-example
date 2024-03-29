package com.philipgurr.composenews.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.material.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import coil.size.Scale
import com.philipgurr.composenews.domain.NewsPost
import com.philipgurr.composenews.domain.Screen

@Composable
fun NewsList(
    padding: PaddingValues,
    posts: List<NewsPost>,
    navigate: (Screen) -> Unit
) {
    val baseModifier = Modifier.padding(padding)
    LazyColumn(
        modifier = baseModifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(posts) {
            NewsListItem(newsPost = it, onClick = { post ->
                navigate(Screen.NewsDetail(post))
            })
        }

    }
}

@Composable
fun NewsListItem(newsPost: NewsPost, onClick: (NewsPost) -> Unit) {
    Card(
        modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 15.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        elevation = 10.dp,
        shape = RoundedCornerShape(30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onClick(newsPost) }),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageUrl = newsPost.urlToImage
            if(imageUrl != null && imageUrl.isNotEmpty()) {
                Image(
                    painter = rememberImagePainter(data = imageUrl, builder = {
                        size(OriginalSize)
                        scale(Scale.FIT)
                    }),
                    contentDescription = "Article Image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)
                        )
                )
            }

            ArticleText(newsPost)
        }
    }
}

@Composable
private fun ArticleText(newsPost: NewsPost) {
    val paddingTitle = Modifier.padding(15.dp, 10.dp, 15.dp, 5.dp)
    val paddingDesc = Modifier.padding(15.dp, 5.dp, 15.dp, 20.dp)
    Text(
        text = newsPost.title ?: "",
        style = MaterialTheme.typography.h6,
        modifier = paddingTitle
    )
    Text(
        text = newsPost.description ?: "",
        style = MaterialTheme.typography.body2,
        modifier = paddingDesc
    )
}