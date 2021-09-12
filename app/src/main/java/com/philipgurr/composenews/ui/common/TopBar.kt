package com.philipgurr.composenews.ui.common

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.philipgurr.composenews.R
import kotlinx.coroutines.launch

@Composable
fun DefaultTopBar(scaffoldState: ScaffoldState, title: String) {
    val scope = rememberCoroutineScope()
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                Icon(painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Drawer")
            }
        }
    )
}