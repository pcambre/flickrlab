package com.pcambre.flickrlab.ui.navigation

sealed class HomeRoute(var screenRoute: String) {
    object Search: HomeRoute("home_search")
    object Detail: HomeRoute("home_detail")
}