package com.mp98.shop.core.presentation.screens.customscaffold.components

import android.app.Activity
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.mp98.shop.R
import com.mp98.shop.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.shop.core.utils.scalableText

@Composable
fun SearchBar(
    productCartViewModel: ProductCartViewModel
) {
    val state by productCartViewModel.productsCartState.collectAsState()

    VoiceLauncher(productCartViewModel = productCartViewModel)
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = state.searchText,
        onValueChange = { newText ->
            productCartViewModel.onSearchTextChanged(newText)
        },
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
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            keyboardController?.hide()
            productCartViewModel.searchProducts()
        }),
        leadingIcon = {
            IconButton(onClick = {
                productCartViewModel.searchProducts()
            }) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = Icons.Rounded.Search.name
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = {
                productCartViewModel.launchVoiceSearch()
            }) {
                Icon(
                    imageVector = Icons.Rounded.Mic,
                    contentDescription = Icons.Rounded.Mic.name
                )
            }
        }
    )
}

@Composable
fun VoiceLauncher(productCartViewModel: ProductCartViewModel){
    val voiceSearchLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val voiceText = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.firstOrNull() ?: ""
            productCartViewModel.onVoiceSearchResult(voiceText)
        }
    }

    LaunchedEffect(Unit) {
        productCartViewModel.setVoiceSearchLauncher(voiceSearchLauncher)
    }
}