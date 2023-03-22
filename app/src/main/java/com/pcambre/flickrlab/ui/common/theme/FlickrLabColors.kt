package com.pcambre.flickrlab.ui.common.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class FlickrLabColor(
    val primary : Color = Color(0xFF5CBD15),
    val secondary : Color = Color(0xFF418843),
    val background: Color = Color(0xFFFFFFFF),
    val text: Color = Color(0xFF000000),
    val LighterGrey: Color = Color(0xFFEAE9ED),
    val LightGrey: Color = Color(0xFFA7A9AB),
    val Grey: Color = Color(0xFFB0B3B9),

    val GreenDark: Color = Color(0xFF418843),
    val Green300: Color = Color(0xFFDBF5D4),
    val Green400: Color = Color(0xFFC0FAA1),
    val Green500: Color = Color(0xFF94E261),
    val Green600: Color = Color(0xFF5CBD15),
    val Green700: Color = Color(0xFF418843),
    val Green900: Color = Color(0xFF00290C),
    val Green1000: Color = Color(0xFF0B1A0F),

    val Brown200: Color = Color(0xFFF7F6F4),

    val White: Color = Color(0xFFFFFFFF)
)

val LocalFlickrLabColors = staticCompositionLocalOf {
    FlickrLabColor()
}