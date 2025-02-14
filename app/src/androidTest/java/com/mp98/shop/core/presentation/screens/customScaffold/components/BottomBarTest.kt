package com.mp98.shop.core.presentation.screens.customScaffold.components

import androidx.activity.compose.setContent
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import com.mp98.shop.MainActivity
import com.mp98.shop.R
import com.mp98.shop.core.domain.model.Product
import com.mp98.shop.core.presentation.screens.customscaffold.components.BottomBar
import com.mp98.shop.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.shop.core.utils.toCurrencyFormat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BottomBarTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var productCartViewModel: ProductCartViewModel

    private val voucher = Product(code = "VOUCHER", name = "Test Product", price = 5.0)
    private val tshirt = Product(code = "TSHIRT", name = "Test Product", price = 20.0)

    @Before
    fun setUp() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            productCartViewModel = hiltViewModel()
            BottomBar(productCartViewModel = productCartViewModel)
        }
        composeTestRule.waitForIdle()
    }

    @Test
    fun bottomBar_displaysTotalAndDiscountCorrectly() {
        composeTestRule.runOnUiThread {
            productCartViewModel.addProductToCart(voucher)
            productCartViewModel.addProductToCart(voucher)
        }

        val total = composeTestRule.activity.getString(R.string.before) + " " +
                (voucher.price*2).toCurrencyFormat()
        val discountTotal = composeTestRule.activity.getString(R.string.total) + " " +
                voucher.price.toCurrencyFormat()

        composeTestRule.waitUntil {
            composeTestRule.onNodeWithText(discountTotal).isDisplayed() &&
            composeTestRule.onNodeWithText(total).isDisplayed()
        }

        composeTestRule.onNodeWithText(discountTotal).assertExists()
        composeTestRule.onNodeWithText(total).assertExists()

        composeTestRule.runOnUiThread {
            productCartViewModel.removeProductFromCart(voucher)
            productCartViewModel.removeProductFromCart(voucher)
        }
    }

    @Test
    fun bottomBar_updatesTotalOnRemovingProduct() {
        composeTestRule.runOnUiThread {
            productCartViewModel.addProductToCart(voucher)
            productCartViewModel.addProductToCart(tshirt)
        }

        val totalBeforeRemoving = composeTestRule.activity.getString(R.string.total) + " " +
                (voucher.price + tshirt.price).toCurrencyFormat()

        composeTestRule.waitUntil {
            composeTestRule.onNodeWithText(totalBeforeRemoving).isDisplayed()
        }

        composeTestRule.onNodeWithText(totalBeforeRemoving).assertExists()


        composeTestRule.runOnUiThread {
            productCartViewModel.removeProductFromCart(voucher)
        }

        val totalAfterRemoving = composeTestRule.activity.getString(R.string.total) + " " +
                tshirt.price.toCurrencyFormat()

        composeTestRule.waitUntil {
            composeTestRule.onNodeWithText(totalAfterRemoving).isDisplayed()
        }

        composeTestRule.onNodeWithText(totalAfterRemoving).assertExists()


        composeTestRule.runOnUiThread {
            productCartViewModel.removeProductFromCart(voucher)
            productCartViewModel.removeProductFromCart(tshirt)
        }
    }

    @Test
    fun bottomBar_displaysCorrectlyWithNoProducts() {
        composeTestRule.waitUntil {
            composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.total) +
                    " " + 0.0.toCurrencyFormat()).isDisplayed()
        }
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.total) +
                " " + 0.0.toCurrencyFormat()).assertExists()
    }
}
