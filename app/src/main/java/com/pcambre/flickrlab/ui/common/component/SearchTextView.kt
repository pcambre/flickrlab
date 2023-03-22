package com.pcambre.flickrlab.ui.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.pcambre.flickrlab.R
import com.pcambre.flickrlab.ui.common.theme.FlickrLabTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextView(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit
) {

    val keyboard = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = query,
                onValueChange = { newQuery: String ->
                    onQueryChange(newQuery)
                },
                singleLine = true,
                trailingIcon = if (query.isNotBlank()) {
                    {
                        IconButton(onClick = { onQueryChange("") }) {
                            Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
                        }
                    }
                } else {
                    null
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_query_hint),
                        style = FlickrLabTheme.typography.caption
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions =  KeyboardActions(
                    onSearch = {
                        keyboard?.hide()
                        focusManager.clearFocus()
                        onSearch()
                    }
                ),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = FlickrLabTheme.colors.Green600,
                    unfocusedBorderColor = Color.Transparent,
                    backgroundColor = FlickrLabTheme.colors.Brown200
                )
            )
        }
    }
}