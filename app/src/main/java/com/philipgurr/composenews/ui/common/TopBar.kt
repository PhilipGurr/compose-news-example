package com.philipgurr.composenews.ui.common

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.vectorResource
import com.philipgurr.composenews.R

@Composable
fun DefaultTopBar(scaffoldState: ScaffoldState, title: String) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(
                onClick = { scaffoldState.drawerState.open() }) {
                Icon(vectorResource(id = R.drawable.ic_launcher_foreground))
            }
        }
    )
}