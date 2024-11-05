package com.mp98.cabifychallenge.core.presentation.screens.cartlist

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import com.mp98.cabifychallenge.MainActivity
import com.mp98.cabifychallenge.R
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CartListTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var productCartViewModel: ProductCartViewModel

    private val voucher = Product(code = "VOUCHER", name = "Cabify Voucher", price = 5.0)
    private val tshirt = Product(code = "TSHIRT", name = "Cabify T-Shirt", price = 20.0)

    @Before
    fun setUp() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            productCartViewModel = hiltViewModel()
            CartList(productCartViewModel = productCartViewModel)
        }
        composeTestRule.waitForIdle()
    }

    @Test
    fun cartList_displaysItemsCorrectly() {
        composeTestRule.runOnUiThread {
            productCartViewModel.addProductToCart(voucher)
            productCartViewModel.addProductToCart(tshirt)
        }

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText(voucher.name).fetchSemanticsNodes().isNotEmpty() &&
            composeTestRule.onAllNodesWithText(tshirt.name).fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText(voucher.name).assertExists()
        composeTestRule.onNodeWithText(tshirt.name).assertExists()
        
        composeTestRule.runOnUiThread {
            productCartViewModel.removeProductFromCart(voucher)
            productCartViewModel.removeProductFromCart(tshirt)
        }
    }

    @Test
    fun cartList_showsEmptyStateCorrectly() {
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.empty_cart_message)).assertExists()
    }
}
