package com.philipgurr.composenews.ui.common

import androidx.compose.foundation.ClickableText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.philipgurr.composenews.R
import com.philipgurr.composenews.viewmodel.Screen

@Composable
fun Drawer(currentScreen: Screen = Screen.NewsList, navigate: (Screen) -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(10.dp),
        horizontalGravity = Alignment.CenterHorizontally
    ) {
        val activatedColor = Color(ContextAmbient.current.getColor(R.color.design_default_color_primary))
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
            style = MaterialTheme.typography.h4 + TextStyle(color = activatedColor),
        ) {}
    } else {
        ClickableText(text = AnnotatedString("Home"), style = MaterialTheme.typography.h4) {
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
            style = MaterialTheme.typography.h4 + TextStyle(color = activatedColor),
        ) {}
    } else {
        ClickableText(text = AnnotatedString("Favorites"), style = MaterialTheme.typography.h4) {
            navigate(Screen.FavoritesList)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Drawer()
}