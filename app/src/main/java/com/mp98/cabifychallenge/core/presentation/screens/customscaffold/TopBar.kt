package com.mp98.cabifychallenge.core.presentation.screens.customscaffold

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mp98.cabifychallenge.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    CenterAlignedTopAppBar(
        title = {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text(stringResource(R.string.search)) },
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
                            contentDescription = "Icono de buscar"
                        )

                    }
                },
                trailingIcon =
                {
                    IconButton(onClick = {})
                    {
                        Icon(
                            //TODO: Add mic icon
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Icono de Micrófono"
                        )

                    }
                }
            )
        },
        actions = {
            IconButton(
                onClick =
                {

                }
            )
            {
                Icon(
                    imageVector = Icons.Rounded.ShoppingCart,
                    contentDescription = stringResource(R.string.shopping_cart_icon_description)
                )
            }
        }
    )
}