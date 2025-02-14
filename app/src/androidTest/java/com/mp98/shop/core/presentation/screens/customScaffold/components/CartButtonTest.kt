package com.mp98.shop.core.presentation.screens.customScaffold.components

import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import com.mp98.shop.MainActivity
import com.mp98.shop.core.domain.model.Product
import com.mp98.shop.core.presentation.screens.customscaffold.components.CartButton
import com.mp98.shop.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.shop.core.utils.toCurrencyFormat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CartButtonTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var productCartViewModel: ProductCartViewModel

    private val voucher = Product(code = "VOUCHER", name = "Test Product", price = 5.0)
    val tshirt = Product(code = "TSHIRT", name = "Test Product", price = 20.0)


    @Before
    fun setUp() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            productCartViewModel = hiltViewModel()
            CartButton(
                productCartViewModel = productCartViewModel,
                onChangeToCart = {}
            )
        }
        composeTestRule.waitForIdle()
    }

    @Test
    fun cartButton_displaysCorrectly() {
        composeTestRule.onNodeWithContentDescription(Icons.Rounded.ShoppingCart.name).assertExists()

        composeTestRule.onNodeWithText(0.0.toCurrencyFormat()).assertExists()

        composeTestRule.runOnUiThread {
            productCartViewModel.addProductToCart(voucher)
        }

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText("1").fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText("1").assertExists()
        composeTestRule.onNodeWithText(5.0.toCurrencyFormat()).assertExists()

        composeTestRule.runOnUiThread {
            productCartViewModel.removeProductFromCart(voucher)
        }
    }

    @Test
    fun cartButton_removesProductFromCart() {
        composeTestRule.runOnUiThread {
            productCartViewModel.addProductToCart(voucher)
        }

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText("1").fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText("1").assertExists()
        composeTestRule.onNodeWithText(5.0.toCurrencyFormat()).assertExists()

        composeTestRule.runOnUiThread {
            productCartViewModel.removeProductFromCart(voucher)
        }

        composeTestRule.waitForIdle()

        composeTestRule.waitUntil {
            composeTestRule.onNodeWithText(0.0.toCurrencyFormat()).isDisplayed()
        }

        composeTestRule.onNodeWithText(0.0.toCurrencyFormat()).assertExists()
    }

    @Test
    fun cartButton_applies2X1DiscountToProduct() {

        composeTestRule.runOnUiThread {
            productCartViewModel.addProductToCart(voucher)
            productCartViewModel.addProductToCart(voucher)
        }

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText("2").fetchSemanticsNodes().isNotEmpty()
        }

        val expectedPrice = 5.0.toCurrencyFormat()

        composeTestRule.onNodeWithText("2").assertExists()
        composeTestRule.onNodeWithText(expectedPrice).assertExists()

        composeTestRule.runOnUiThread {
            productCartViewModel.removeProductFromCart(voucher)
            productCartViewModel.removeProductFromCart(voucher)
        }
    }

    @Test
    fun cartButton_appliesBulkDiscountToProduct() {

        composeTestRule.runOnUiThread {
            productCartViewModel.addProductToCart(tshirt)
            productCartViewModel.addProductToCart(tshirt)
            productCartViewModel.addProductToCart(tshirt)
        }

        val expectedPrice = 57.0.toCurrencyFormat()

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText("3").fetchSemanticsNodes().isNotEmpty()
            composeTestRule.onAllNodesWithText(expectedPrice).fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText("3").assertExists()
        composeTestRule.onNodeWithText(expectedPrice).assertExists()

        composeTestRule.runOnUiThread {
            productCartViewModel.removeProductFromCart(tshirt)
            productCartViewModel.removeProductFromCart(tshirt)
            productCartViewModel.removeProductFromCart(tshirt)
        }
    }

}

