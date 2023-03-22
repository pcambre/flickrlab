package com.pcambre.flickrlab.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.pcambre.flickrlab.ui.common.component.FlickrLabAsyncImage
import com.pcambre.flickrlab.ui.common.theme.FlickrLabTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PhotoRow(
    modifier: Modifier,
    title: String,
    thumbnailUrl: String,
    onClick: () -> Unit
) {
    Card(modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), onClick = onClick) {
        Row(
            modifier = Modifier.padding(FlickrLabTheme.spaces.m),
            horizontalArrangement = Arrangement.spacedBy(FlickrLabTheme.spaces.m),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FlickrLabAsyncImage(
                modifier = Modifier.size(75.dp),
                url = thumbnailUrl,
                contentDescription = title
            )
            Text(
                text = title,
                style = FlickrLabTheme.typography.body1,
                color = FlickrLabTheme.colors.text,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}