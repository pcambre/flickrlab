package com.pcambre.flickrlab.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.pcambre.flickrlab.data.model.PhotoDTO
import com.pcambre.flickrlab.ui.common.component.FlickrLabAsyncImage
import com.pcambre.flickrlab.ui.common.theme.FlickrLabTheme

//TODO: Map PhotoDTO into a UI entity
@Composable
fun DetailScreen(photo: PhotoDTO, onCloseScreen: () -> Unit) {

    Box(
        Modifier
            .fillMaxSize()
            .background(FlickrLabTheme.colors.text.copy(alpha = 0.7F))
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = FlickrLabTheme.spaces.ml)
        ) {
            Column(horizontalAlignment = Alignment.End) {
                IconButton(
                    onClick = { onCloseScreen() }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = null,
                        tint = FlickrLabTheme.colors.White
                    )
                }
            }
            FlickrLabAsyncImage(
                url = photo.originalUrl.orEmpty(),
                contentDescription = photo.title
            )
            Text(
                modifier = Modifier.padding(top = FlickrLabTheme.spaces.l),
                text = photo.title,
                style = FlickrLabTheme.typography.body1,
                color = FlickrLabTheme.colors.White,
                textAlign = TextAlign.Center
            )
        }
    }

}