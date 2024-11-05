package com.mp98.cabifychallenge.core.presentation.screens.customScaffold.components

import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import com.mp98.cabifychallenge.MainActivity
import com.mp98.cabifychallenge.R
import com.mp98.cabifychallenge.core.presentation.screens.customscaffold.components.TopBar
import com.mp98.cabifychallenge.core.presentation.screens.navigation.NavigationRoute
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class TopBarTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var productCartViewModel: ProductCartViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        
        composeTestRule.activity.setContent {
            productCartViewModel = hiltViewModel()

            TopBar(
                productCartViewModel = productCartViewModel,
                onChangeToCart = { productCartViewModel.changeScreen(NavigationRoute.CartListScreen) }
            )
        }
        composeTestRule.waitForIdle()
    }

    @Test
    fun topBar_displaysSearchBarInProductScreen() {
        composeTestRule.runOnUiThread {
            productCartViewModel.changeScreen(NavigationRoute.ProductListScreen)
        }
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithContentDescription(Icons.Rounded.Search.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.your_purchase)).assertDoesNotExist()
    }

    @Test
    fun topBar_doesNotDisplaySearchBarInCartScreen() {
        composeTestRule.runOnUiThread {
            productCartViewModel.changeScreen(NavigationRoute.CartListScreen)
        }
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.your_purchase))
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(Icons.Rounded.ShoppingCart.name).assertDoesNotExist()
    }

    @Test
    fun topBar_displaysCartButtonInProductScreen() {
        composeTestRule.runOnUiThread {
            productCartViewModel.changeScreen(NavigationRoute.ProductListScreen)
        }
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithContentDescription(Icons.Rounded.ShoppingCart.name).assertIsDisplayed()
    }

    @Test
    fun topBar_doesNotDisplayCartButtonInCartScreen() {
        composeTestRule.runOnUiThread {
            productCartViewModel.changeScreen(NavigationRoute.CartListScreen)
        }
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithContentDescription(Icons.Rounded.ShoppingCart.name).assertIsNotDisplayed()
    }
}