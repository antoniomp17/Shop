package com.mp98.cabifychallenge.core.presentation.screens.customscaffold.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.mp98.cabifychallenge.R
import com.mp98.cabifychallenge.core.utils.scalableText

@Composable
fun SearchBar(){
    OutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = {
            Text(
                text = stringResource(R.string.search),
                fontSize = scalableText(16.sp),
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        singleLine = true,
        shape = MaterialTheme.shapes.small,
        leadingIcon =
        {
            IconButton(onClick = {})
            {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = Icons.Rounded.Search.name
                )

            }
        },
        trailingIcon =
        {
            IconButton(onClick = {})
            {
                Icon(
                    imageVector = Icons.Rounded.Mic,
                    contentDescription = Icons.Rounded.Mic.name
                )

            }
        }
    )
}