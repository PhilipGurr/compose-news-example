package com.philipgurr.composenews.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.philipgurr.composenews.di.AppContainer
import com.philipgurr.composenews.ui.detail.NewsDetailScreen
import com.philipgurr.composenews.ui.favorites.FavoritesListScreen
import com.philipgurr.composenews.ui.newslist.NewsListScreen
import com.philipgurr.composenews.domain.Screen
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun NewsApp(appContainer: AppContainer) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "newsList") {
        composable("newsList") {
            NewsListScreen(appContainer.newsRepository, navController::toScreen)
        }
        composable("favoritesList") {
            FavoritesListScreen(appContainer.newsRepository, navController::toScreen)
        }
        composable(
            "detail/{post}",
            arguments = listOf(navArgument("post") { type = NavType.StringType })) { backstackEntry ->
            val post = backstackEntry.arguments?.getString("post", "") ?: ""
            NewsDetailScreen(
                appContainer.newsRepository,
                Json.decodeFromString(post),
                navController::toScreen
            )
        }
    }
}

private fun NavController.toScreen(screen: Screen) {
    when(screen) {
        is Screen.Previous -> popBackStack()
        is Screen.NewsDetail -> {
            val newsPostUrl = "detail/" + Json.encodeToString(screen.newsPost)
            navigate(newsPostUrl)
        }
        else -> navigate(screen.navUrl)
    }
}