package com.pcambre.flickrlab.ui.navigation

sealed class BookmarkRoute(var screenRoute: String) {
    object Main: BookmarkRoute("bookmark_main")
    object Detail: BookmarkRoute("bookmark_detail")
}