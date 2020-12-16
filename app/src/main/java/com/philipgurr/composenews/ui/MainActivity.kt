package com.philipgurr.composenews.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.philipgurr.composenews.ComposeNewsApplication
import com.philipgurr.composenews.ui.style.ComposeNewsTheme
import com.philipgurr.composenews.viewmodel.NavigationViewModel
import com.philipgurr.composenews.viewmodel.Screen

class MainActivity : AppCompatActivity() {
    private val navigationViewModel: NavigationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as ComposeNewsApplication).appContainer

        setContent {
            ComposeNewsTheme {
                NewsApp(appContainer = appContainer)
            }
        }
    }

    override fun onBackPressed() {
        if(navigationViewModel.currentScreen.value is Screen.NewsDetail) {
            navigationViewModel.navigateBack()
        } else {
            super.onBackPressed()
        }
    }
}