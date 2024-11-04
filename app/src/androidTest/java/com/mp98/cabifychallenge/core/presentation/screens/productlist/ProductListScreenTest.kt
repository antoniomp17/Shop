package com.mp98.cabifychallenge.core.presentation.screens.productlist

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.filters.LargeTest
import com.mp98.cabifychallenge.MainActivity
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@LargeTest
@HiltAndroidTest
class ProductListScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            val productCartViewModel = hiltViewModel<ProductCartViewModel>()
            productCartViewModel.onSearchTextChanged("")
            ProductList(productCartViewModel = productCartViewModel)
        }
        composeTestRule.waitForIdle()
    }

    @Test
    fun testProductsDisplayedInGrid() {
        
        composeTestRule
            .onNodeWithTag("ProductGrid")
            .assertExists()
            .performScrollToIndex(0)
            .assertIsDisplayed()

        composeTestRule
            .onAllNodesWithTag("ProductCard")
            .onFirst()
            .assertIsDisplayed()
    }

    @Test
    fun testOpenDiscountDialog() {
        composeTestRule
            .onAllNodesWithTag("DiscountableProduct", useUnmergedTree = true)
            .onFirst()
            .performClick()

        composeTestRule
            .onNodeWithTag("DiscountDialog")
            .assertIsDisplayed()
    }

    @Test
    fun testGridScrolling() {
        composeTestRule
            .onNodeWithTag("ProductGrid")
            .performScrollToIndex(2)

        composeTestRule
            .onAllNodesWithTag("ProductCard")
            .onLast()
            .assertIsDisplayed()
    }
}