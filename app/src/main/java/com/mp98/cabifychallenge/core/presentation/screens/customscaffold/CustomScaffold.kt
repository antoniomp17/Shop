package com.mp98.cabifychallenge.core.presentation.screens.customscaffold

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mp98.cabifychallenge.core.presentation.screens.customscaffold.components.BottomBar
import com.mp98.cabifychallenge.core.presentation.screens.customscaffold.components.TopBar
import com.mp98.cabifychallenge.core.presentation.screens.navigation.NavigationRoute
import com.mp98.cabifychallenge.core.presentation.states.ProductsCartState
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.cabifychallenge.core.utils.ErrorType
import com.mp98.cabifychallenge.core.utils.dynamicPadding
import com.mp98.cabifychallenge.core.utils.scalableText
import com.mp98.cabifychallenge.ui.theme.primaryColor

@Composable
fun CustomScaffold(
    modifier: Modifier,
    productCartViewModel: ProductCartViewModel,
    content: @Composable () -> Unit,
    onChangeToCart: () -> Unit,
    onChangeToProducts: () -> Unit
) {

    val state by productCartViewModel.productsCartState.collectAsState()

    BackPressHandler(state = state){
        productCartViewModel.changeScreen(NavigationRoute.ProductListScreen)
        onChangeToProducts()
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (state.screen == NavigationRoute.CartListScreen){
                BottomBar(productCartViewModel = productCartViewModel)
            }
        },
        topBar = {
            TopBar(productCartViewModel = productCartViewModel){
                productCartViewModel.changeScreen(NavigationRoute.CartListScreen)
                onChangeToCart()
            }
        },
        floatingActionButton = {
            if(state.screen == NavigationRoute.CartListScreen) {
                Box(Modifier.dynamicPadding()){
                    FloatingActionButton(
                        contentColor = Color.White,
                        shape = RoundedCornerShape(50.dp),
                        onClick = {
                            productCartViewModel.changeScreen(NavigationRoute.ProductListScreen)
                            onChangeToProducts()
                        },
                        modifier = Modifier
                            .testTag("FloatingActionButton")
                            .align(Alignment.BottomCenter).offset(y = 60.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ShoppingBag,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(fraction = 0.05f)
                        )
                    }
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ){ padding ->
        Column(modifier = Modifier
            .padding(padding).fillMaxSize()) {
            HorizontalDivider(
                thickness = 0.5.dp
            )
            if(state.error != null){
                Box(
                    modifier = Modifier.fillMaxSize().dynamicPadding(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = ErrorType.getErrorTypeMessage(state.error!!),
                        fontSize = scalableText(22.sp),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = primaryColor
                    )
                }
            } else {
                content()
            }
        }
    }
}

@Composable
fun BackPressHandler(
    state: ProductsCartState,
    onChangeToProducts: () -> Unit
){

    val context = LocalContext.current
    if(state.screen == NavigationRoute.CartListScreen){
        BackHandler(enabled = true) {
            onChangeToProducts()
        }
    } else {
        BackHandler(enabled = true) {
            (context as? Activity)?.finish()
        }
    }
}