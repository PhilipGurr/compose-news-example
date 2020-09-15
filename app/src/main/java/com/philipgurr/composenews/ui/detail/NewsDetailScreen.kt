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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.philipgurr.composenews.R
import com.philipgurr.composenews.data.NewsRepository
import com.philipgurr.composenews.domain.NewsPost
import kotlinx.coroutines.launch

@Composable
fun NewsDetailScreen(newsRepository: NewsRepository, newsPost: NewsPost) {
    val scope = rememberCoroutineScope()
    val isFavorite = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Article") },
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            newsRepository.addFavorite(newsPost)
                            isFavorite.value = true
                        }
                    }) {
                        Icon(asset = vectorResource(id = if(isFavorite.value) R.drawable.favorite_filled else R.drawable.favorite_empty))
                    }
                }
            )
        },
        bodyContent = {
            Text(text = "hi again")
        }
    )
}