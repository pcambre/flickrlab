package com.pcambre.flickrlab.ui.home

import FullScreenLoadingView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.pcambre.flickrlab.ui.navigation.NavigationGraph

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {

    val navController = rememberAnimatedNavController()
    val loadingState = rememberSaveable { mutableStateOf(false) }
    val bottomBarState = rememberSaveable { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarState.value,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                if (bottomBarState.value) {
                    FlickrLabBottomNavigation(navController = navController)
                }
            }
        }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(
                navController = navController,
                loadingState = loadingState,
                bottomNavBarVisibility = bottomBarState
            )
        }
    }
    if (loadingState.value) {
        FullScreenLoadingView(Modifier.fillMaxSize())
    }
}