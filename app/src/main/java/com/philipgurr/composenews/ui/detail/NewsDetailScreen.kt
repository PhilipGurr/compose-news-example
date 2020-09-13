package com.philipgurr.composenews.ui.detail

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.philipgurr.composenews.R
import com.philipgurr.composenews.domain.NewsPost

@Composable
fun NewsDetailScreen(newsPost: NewsPost) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Article") },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(asset = vectorResource(id = R.drawable.favorite_empty))
                    }
                }
            )
        },
        bodyContent = {
            Text(text = "hi again")
        }
    )
}

@Preview
@Composable
fun Preview() {
    NewsDetailScreen(
        newsPost = NewsPost(
            "Philip Gurr",
            "Trump elected again",
            "Small description",
            "",
            "https://static.politico.com/7a/03/4ff6924c41dfaaa873b00d0dcc94/200323-mike-bloomberg-gty-773.jpg",
            "Big text content"
        )
    )
}

sealed class MenuAction(
    val label: String,
    @DrawableRes val icon: Int
) {
    class Favorite : MenuAction("Favorites", R.drawable.favorite_empty)
}