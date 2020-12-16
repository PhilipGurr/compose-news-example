package com.philipgurr.composenews.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.philipgurr.composenews.ComposeNewsApplication
import com.philipgurr.composenews.ui.style.ComposeNewsTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as ComposeNewsApplication).appContainer

        setContent {
            ComposeNewsTheme {
                NewsApp(appContainer = appContainer)
            }
        }
    }
}