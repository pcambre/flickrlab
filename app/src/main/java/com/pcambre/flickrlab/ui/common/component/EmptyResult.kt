package com.pcambre.flickrlab.ui.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.pcambre.flickrlab.ui.common.theme.FlickrLabTheme

@Composable
fun EmptyResultView(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = FlickrLabTheme.spaces.ml),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier,
            text = title,
            style = FlickrLabTheme.typography.h1,
            color = FlickrLabTheme.colors.text,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.padding(vertical = FlickrLabTheme.spaces.ml),
            text = subtitle,
            style = FlickrLabTheme.typography.body1,
            color = FlickrLabTheme.colors.LightGrey,
            textAlign = TextAlign.Center
        )
    }
}