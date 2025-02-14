package com.mp98.shop.core.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.dynamicPadding(): Modifier {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val padding = screenWidth * 0.02f
    return this.padding(padding)
}