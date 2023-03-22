package com.pcambre.flickrlab.ui.common.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class FlickrLabSpaces(
    val xxs: Dp = 2.dp,
    val xs: Dp = 4.dp,
    val s: Dp = 8.dp,
    val sm: Dp = 12.dp,
    val m: Dp = 16.dp,
    val ml: Dp = 24.dp,
    val l: Dp = 32.dp,
    val xl: Dp = 64.dp,
)

val LocalSpaces = staticCompositionLocalOf { FlickrLabSpaces() }