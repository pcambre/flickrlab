package com.pcambre.flickrlab.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pcambre.flickrlab.ui.common.theme.FlickrLabTheme
import com.pcambre.flickrlab.R
import com.pcambre.flickrlab.ui.navigation.BottomNavItem

@Composable
fun FlickrLabBottomNavigation(navController: NavController) {
    val screens = listOf(
        BottomNavItem.Home,
        BottomNavItem.BookmarkedPictures
    )

    BottomNavigation(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.bottom_bar_height)),
        backgroundColor = FlickrLabTheme.colors.background,
        contentColor = FlickrLabTheme.colors.text
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        screens.forEach { screen ->
            val isSelected = currentRoute == screen.screenRoute

            BottomNavigationItem(
                icon = {
                    TabIcon(
                        isSelected,
                        screen.iconSelected,
                        screen.iconUnselected,
                        screen.titleId
                    )
                },
                selectedContentColor = FlickrLabTheme.colors.Grey,
                unselectedContentColor = FlickrLabTheme.colors.text,
                alwaysShowLabel = true,
                selected = isSelected,
                onClick = { onTabClick(navController, screen) }
            )
        }
    }
}

private fun onTabClick(navController: NavController, screen: BottomNavItem) {
    navController.navigate(screen.screenRoute) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
private fun TabIcon(
    isSelected: Boolean,
    iconSelected: Int,
    iconUnselected: Int,
    titleId: Int
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                painterResource(id = if (isSelected) iconSelected else iconUnselected),
                modifier = Modifier.size(22.dp),
                tint = Color.Unspecified,
                contentDescription = stringResource(id = titleId)
            )
        }
        TabLabel(isSelected, titleId)
    }
}

@Composable
private fun TabLabel(isSelected: Boolean, titleId: Int) {

    val textColor =
        if (isSelected) FlickrLabTheme.colors.primary else FlickrLabTheme.colors.LightGrey
    Text(
        modifier = Modifier.padding(top = 10.dp),
        text = stringResource(id = titleId),
        style = FlickrLabTheme.typography.caption,
        color = textColor
    )
}