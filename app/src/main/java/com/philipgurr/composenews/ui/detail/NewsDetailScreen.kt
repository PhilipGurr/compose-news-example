package com.philipgurr.composenews.ui.detail

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.philipgurr.composenews.R
import com.philipgurr.composenews.data.NewsRepository
import com.philipgurr.composenews.domain.NewsPost
import com.philipgurr.composenews.viewmodel.NavigationViewModel
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.launch

@Composable
fun NewsDetailScreen(navigationViewModel: NavigationViewModel, newsRepository: NewsRepository, newsPost: NewsPost) {
    val scope = rememberCoroutineScope()
    val isFavorite = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Article") },
                navigationIcon = {
                    IconButton(
                        icon = { Icon(asset = vectorResource(id = R.drawable.arrow_back)) },
                        onClick = { navigationViewModel.navigateBack() })
                },
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            newsRepository.addFavorite(newsPost)
                            isFavorite.value = true
                        }
                    }) {
                        Icon(asset = vectorResource(id = if (isFavorite.value) R.drawable.favorite_filled else R.drawable.favorite_empty))
                    }
                }
            )
        },
        bodyContent = {
            Article(newsPost = newsPost)
        }
    )
}

@Composable
fun Article(newsPost: NewsPost) {
    ConstraintLayout {
        val (image, title, articleText) = createRefs()

        val imageUrl = newsPost.urlToImage
        if(imageUrl != null && imageUrl.isNotEmpty()) {
            CoilImage(
                data = newsPost.urlToImage,
                modifier = Modifier.fillMaxWidth().constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        }


        Text(
            text = newsPost.title ?: "",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.fillMaxWidth().padding(10.dp).constrainAs(title) {
                top.linkTo(image.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        Text(
            text = newsPost.content ?: "No content!",
            style = MaterialTheme.typography.body2,
            modifier = Modifier.fillMaxWidth().padding(10.dp).constrainAs(articleText) {
                top.linkTo(title.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
    }
}