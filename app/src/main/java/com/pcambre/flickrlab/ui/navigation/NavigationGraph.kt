package com.pcambre.flickrlab.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pcambre.flickrlab.data.model.PhotoDTO
import com.pcambre.flickrlab.ui.detail.DetailScreen
import com.pcambre.flickrlab.ui.home.SearchScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    loadingState: MutableState<Boolean>,
    bottomNavBarVisibility: MutableState<Boolean>,
) {

    NavHost(navController, startDestination = HomeRoute.Search.screenRoute) {

        composable(HomeRoute.Search.screenRoute) {
            bottomNavBarVisibility.value = true
            SearchScreen(showLoading = { show -> loadingState.value = show },
                onPhotoSelected = { photo ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "photo",
                        value = photo
                    )
                    navController.navigate(HomeRoute.Detail.screenRoute)
                })
        }

        composable(HomeRoute.Detail.screenRoute) {
            bottomNavBarVisibility.value = false
            val savedBack = navController.previousBackStackEntry?.savedStateHandle
            savedBack?.apply {
                get<PhotoDTO>("photo")?.let { photo ->
                    DetailScreen(photo = photo) {
                        navController.popBackStack()
                    }
                }
            }
        }

        composable(BookmarkRoute.Main.screenRoute) {

        }
    }
}