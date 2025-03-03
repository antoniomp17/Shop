package com.mp98.shop.core.presentation.screens.customscaffold.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun BackButton(
    onChangeToProducts: () -> Unit
){
    IconButton(
        onClick = {
            onChangeToProducts()
        }
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = Icons.AutoMirrored.Default.ArrowBack.name,
        )
    }
}