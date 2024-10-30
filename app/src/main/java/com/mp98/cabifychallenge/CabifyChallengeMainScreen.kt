package com.mp98.cabifychallenge

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mp98.cabifychallenge.core.presentation.screens.customscaffold.CustomScaffold
import com.mp98.cabifychallenge.core.presentation.screens.navigation.NavigationHost
import com.mp98.cabifychallenge.ui.theme.CabifyChallengeTheme

@Composable
fun CabifyChallengeMainContent(){
    CabifyChallengeTheme{
        val navHostController = rememberNavController()

        CustomScaffold(modifier = Modifier
            .fillMaxSize(),
            content = {
                NavigationHost(navHostController = navHostController)
            }
        )
    }
}