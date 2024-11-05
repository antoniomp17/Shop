package com.mp98.cabifychallenge.core.presentation.screens.cartlist.components

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.mp98.cabifychallenge.MainActivity
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.cabifychallenge.core.utils.toCurrencyFormat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CartItemTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var productCartViewModel: ProductCartViewModel

    private val voucher = Product(code = "VOUCHER", name = "Cabify Voucher", price = 5.0)

    @Before
    fun setUp() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            productCartViewModel = hiltViewModel()
            CartItem(
                product = voucher,
                productCartViewModel = productCartViewModel,
                onSelectProduct = {}
            )
        }
        composeTestRule.waitForIdle()
    }

    @Test
    fun cartItem_displaysProductDetailsCorrectly() {
        composeTestRule.runOnUiThread {
            productCartViewModel.addProductToCart(voucher)
        }

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText(voucher.name).fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText(voucher.name).assertExists()
        composeTestRule.onNodeWithText(voucher.price.toCurrencyFormat()).assertExists()
        composeTestRule.onNodeWithContentDescription(voucher.name).assertExists()

        composeTestRule.runOnUiThread {
            productCartViewModel.removeProductFromCart(voucher)
        }
    }

    @Test
    fun cartItem_updatesTotalCorrectly() {
        composeTestRule.runOnUiThread {
            productCartViewModel.addProductToCart(voucher)
        }

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText(voucher.price.toCurrencyFormat()).fetchSemanticsNodes().isNotEmpty()
        }

        val total = voucher.price * productCartViewModel.getProductsOfCode(voucher.code).size

        composeTestRule.onNodeWithText(total.toCurrencyFormat()).assertExists()

        composeTestRule.runOnUiThread {
            productCartViewModel.removeProductFromCart(voucher)
        }
    }

    @Test
    fun cartItem_callsOnSelectProduct() {
        var selected = false
        composeTestRule.activity.setContent {
            productCartViewModel = hiltViewModel()
            CartItem(
                product = voucher,
                productCartViewModel = productCartViewModel,
                onSelectProduct = { selected = true }
            )
        }

        composeTestRule.runOnUiThread {
            productCartViewModel.addProductToCart(voucher)
        }

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText(voucher.name).fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText(voucher.name).performClick()

        assertThat(selected, `is`(true))

        composeTestRule.runOnUiThread {
            productCartViewModel.removeProductFromCart(voucher)
        }
    }
}
