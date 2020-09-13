package com.philipgurr.composenews.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.philipgurr.composenews.ComposeNewsApplication
import com.philipgurr.composenews.ui.style.ComposeNewsTheme
import com.philipgurr.composenews.viewmodel.NavigationViewModel

class MainActivity : AppCompatActivity() {
    private val navigationViewModel: NavigationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as ComposeNewsApplication).appContainer

        setContent {
            ComposeNewsTheme {
                NewsApp(navigationViewModel = navigationViewModel, appContainer = appContainer)
            }
        }
    }
}