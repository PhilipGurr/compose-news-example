package com.philipgurr.composenews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.philipgurr.composenews.domain.NewsPost

sealed class Screen {
    object NewsList : Screen()
    data class NewsDetail(val newsPost: NewsPost) : Screen()
}

class NavigationViewModel : ViewModel() {
    private val _currentScreen = MutableLiveData<Screen>(Screen.NewsList)
    val currentScreen: LiveData<Screen> = _currentScreen
    private var previousScreen: Screen = Screen.NewsList

    fun navigateTo(screen: Screen) {
        previousScreen = _currentScreen.value ?: return
        _currentScreen.value = screen
    }

    fun navigateBack() {
        _currentScreen.value = previousScreen
    }
}