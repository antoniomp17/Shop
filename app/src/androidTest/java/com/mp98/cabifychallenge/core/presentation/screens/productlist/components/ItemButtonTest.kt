package com.mp98.cabifychallenge.core.presentation.screens.productlist.components

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.filters.LargeTest
import com.mp98.cabifychallenge.MainActivity
import com.mp98.cabifychallenge.R
import com.mp98.cabifychallenge.core.domain.cart.discount.DiscountType
import com.mp98.cabifychallenge.core.domain.cart.discount.TwoForOneDiscount
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.domain.model.Product.Companion.VOUCHER
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@LargeTest
class ItemButtonTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var productCartViewModel: ProductCartViewModel

    private val sampleProduct = Product(code=VOUCHER, name="Cabify Voucher", price=5.0,
        discount = DiscountType.TWO_FOR_ONE_DISCOUNT, discountPrice=0.0, minQuantity=0)

    @Before
    fun setUp() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            productCartViewModel = hiltViewModel()
            ItemButton(
                product = sampleProduct,
                productCartViewModel = productCartViewModel
            )
        }
        composeTestRule.waitForIdle()
    }

    @Test
    fun itemButton_showsAddButtonWhenProductNotInCart() {
        productCartViewModel.removeProductFromCart(sampleProduct)
        composeTestRule.waitForIdle()

        composeTestRule.waitUntil(timeoutMillis = 1000) {
            composeTestRule.onNodeWithTag("AddButton").isDisplayed()
        }

        composeTestRule.onNodeWithTag("AddButton").assertIsDisplayed()
    }

    @Test
    fun itemButton_showsAddOrTakeOutButtonWhenProductInCart() {
        productCartViewModel.addProductToCart(sampleProduct)
        composeTestRule.waitForIdle()

        composeTestRule.waitUntil(timeoutMillis = 1000) {
            composeTestRule.onNodeWithTag("AddOrTakeOutButton").isDisplayed()
        }
        composeTestRule.onNodeWithTag("AddOrTakeOutButton").assertIsDisplayed()
    }
}