package com.pcambre.flickrlab.ui.common.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.pcambre.flickrlab.R

private val FlickrLabFamily = FontFamily(
    Font(
        resId = R.font.roboto_regular,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.roboto_black,
        weight = FontWeight.Black,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.roboto_black_italic,
        weight = FontWeight.Black,
        style = FontStyle.Italic
    ),
    Font(
        resId = R.font.roboto_bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.roboto_medium,
        weight = FontWeight.Medium,
        style = FontStyle.Normal
    )
)

@Immutable
data class FlickrLabTypography constructor(
    val h1: TextStyle = TextStyle(
        fontFamily = FlickrLabFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
    ),
    val h2: TextStyle = TextStyle(
        fontFamily = FlickrLabFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    val caption: TextStyle = TextStyle(
        fontFamily = FlickrLabFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 19.sp,
    ),
    val error: TextStyle = TextStyle(
        fontFamily = FlickrLabFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 14.sp,
    ),
    val body1: TextStyle = TextStyle(
        fontFamily = FlickrLabFamily,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.sp
    ),
)

val LocalFlickrLabTypography = staticCompositionLocalOf {
    FlickrLabTypography()
}
