package com.philipgurr.composenews.ui.common

import androidx.compose.foundation.ClickableText
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.philipgurr.composenews.R
import com.philipgurr.composenews.viewmodel.Screen

@Composable
fun Drawer(currentScreen: Screen, navigate: (Screen) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val activatedColor = Color(AmbientContext.current.getColor(R.color.design_default_color_primary))


        showHome(currentScreen, activatedColor, navigate)
        showFavorites(currentScreen, activatedColor, navigate)
    }
}

@Composable
private fun showHome(
    currentScreen: Screen,
    activatedColor: Color,
    navigate: (Screen) -> Unit
) {
    if (currentScreen is Screen.NewsList) {
        ClickableText(
            text = AnnotatedString("Home"),
            style = MaterialTheme.typography.h4 + TextStyle(color = activatedColor, textAlign = TextAlign.Center),
            modifier = Modifier.fillMaxWidth()
        ) { }
    } else {
        ClickableText(text = AnnotatedString("Home"),
            style = MaterialTheme.typography.h4 + TextStyle(textAlign = TextAlign.Center)) {
            navigate(Screen.NewsList)
        }
    }
}

@Composable
private fun showFavorites(
    currentScreen: Screen,
    activatedColor: Color,
    navigate: (Screen) -> Unit
) {
    if (currentScreen is Screen.FavoritesList) {
        ClickableText(
            text = AnnotatedString("Favorites"),
            style = MaterialTheme.typography.h4 + TextStyle(color = activatedColor, textAlign = TextAlign.Center),
        ) {}
    } else {
        ClickableText(text = AnnotatedString("Favorites"), style = MaterialTheme.typography.h4 + TextStyle(textAlign = TextAlign.Center)) {
                navigate(Screen.FavoritesList)
        }
    }
}