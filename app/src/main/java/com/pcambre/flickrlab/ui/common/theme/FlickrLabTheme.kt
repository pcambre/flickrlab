package com.pcambre.flickrlab.ui.common.theme

import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object FlickrLabTheme {
    val colors: FlickrLabColor
        @Composable
        @ReadOnlyComposable
        get() = LocalFlickrLabColors.current

    val typography: FlickrLabTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalFlickrLabTypography.current

    val spaces: FlickrLabSpaces
        @Composable
        @ReadOnlyComposable
        get() = LocalSpaces.current

}

//TODO: Implement dark/light themes

@Composable
fun FlickrLabTheme(
    spaces: FlickrLabSpaces = FlickrLabTheme.spaces,
    typography: FlickrLabTypography = FlickrLabTheme.typography,
    colors: FlickrLabColor = FlickrLabTheme.colors,
    content: @Composable() () -> Unit
) {
    CompositionLocalProvider(
        LocalFlickrLabColors provides colors,
        LocalSpaces provides spaces,
        LocalFlickrLabTypography provides typography
    ) {
        ProvideTextStyle(typography.body1, content = content)
    }
}