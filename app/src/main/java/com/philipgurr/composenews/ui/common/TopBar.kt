package com.philipgurr.composenews.ui.common

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.vectorResource
import com.philipgurr.composenews.R

@Composable
fun DefaultTopBar(scaffoldState: ScaffoldState) {
    TopAppBar(
        title = { Text(text = "News") },
        navigationIcon = {
            IconButton(
                icon = { Icon(asset = vectorResource(id = R.drawable.ic_launcher_foreground)) },
                onClick = { scaffoldState.drawerState.open() })
        }
    )
}