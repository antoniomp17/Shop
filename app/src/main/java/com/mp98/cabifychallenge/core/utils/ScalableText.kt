package com.mp98.cabifychallenge.core.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun scalableText(
    fontSize: TextUnit = 16.sp
): TextUnit {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val scaledFontSize = (fontSize.value * screenWidth.value / 360).sp

    return scaledFontSize
}