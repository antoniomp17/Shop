package com.mp98.cabifychallenge.core.presentation.screens.customscaffold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel

@Composable
fun CustomScaffold(
    modifier: Modifier,
    productCartViewModel: ProductCartViewModel,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { TopBar(productCartViewModel = productCartViewModel) },
    ){ padding ->
        Column(modifier = Modifier
            .padding(padding)) {
            HorizontalDivider(
                thickness = 0.5.dp
            )
            content()
        }
    }
}