package com.pcambre.flickrlab.ui.common.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pcambre.flickrlab.R
import com.pcambre.flickrlab.ui.common.theme.FlickrLabTheme

@Composable
fun ErrorView(onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = FlickrLabTheme.spaces.ml),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.error_title),
            style = FlickrLabTheme.typography.h1,
            color = FlickrLabTheme.colors.text,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.padding(vertical = FlickrLabTheme.spaces.ml),
            text = stringResource(id = R.string.error_sub_title),
            style = FlickrLabTheme.typography.body1,
            color = FlickrLabTheme.colors.LightGrey,
            textAlign = TextAlign.Center
        )

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.button_height))
                .padding(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = FlickrLabTheme.colors.Green500,
                disabledBackgroundColor = FlickrLabTheme.colors.Green500.copy(alpha = 0.3f)
            ),
            shape = RoundedCornerShape(16.dp),
            onClick = onRetry
        ) {
            Text(
                text = stringResource(id = R.string.error_retry_button),
                style = FlickrLabTheme.typography.caption,
                color = FlickrLabTheme.colors.text
            )
        }
    }
}