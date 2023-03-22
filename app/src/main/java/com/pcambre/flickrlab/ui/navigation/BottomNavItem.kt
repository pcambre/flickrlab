package com.pcambre.flickrlab.ui.navigation

import androidx.annotation.StringRes
import com.pcambre.flickrlab.R

sealed class BottomNavItem(
    @StringRes val titleId: Int,
    var iconSelected: Int,
    var iconUnselected: Int,
    var screenRoute: String
) {

    object Home : BottomNavItem(
        R.string.bottom_bar_home,
        R.drawable.ic_home_filled,
        R.drawable.ic_home,
        HomeRoute.Search.screenRoute
    )

    object BookmarkedPictures : BottomNavItem(
        R.string.bottom_bar_bookmark,
        R.drawable.ic_bookmark_filled,
        R.drawable.ic_bookmark,
        BookmarkRoute.Main.screenRoute
    )
}