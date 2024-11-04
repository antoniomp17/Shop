package com.mp98.cabifychallenge.core.presentation.screens.productlist.components

import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.filters.LargeTest
import com.mp98.cabifychallenge.MainActivity
import com.mp98.cabifychallenge.R
import com.mp98.cabifychallenge.core.domain.cart.discount.DiscountType
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.domain.model.Product.Companion.VOUCHER
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.cabifychallenge.core.utils.toCurrencyFormat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@LargeTest
class ProductItemTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var productCartViewModel: ProductCartViewModel

    private val sampleProduct = Product(
        code = VOUCHER, name = "Cabify Voucher", price = 5.0,
        discount = DiscountType.TWO_FOR_ONE_DISCOUNT, discountPrice = 0.0, minQuantity = 0
    )

    private val sampleProductWithoutDiscount = sampleProduct.copy(discount = null)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun productItem_showsDiscountInfoWhenProductHasDiscount() {
        composeTestRule.activity.setContent {
            productCartViewModel = hiltViewModel()
            ProductItem(
                product = sampleProduct,
                productCartViewModel = productCartViewModel,
                onSelectProduct = {}
            )
        }
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText(DiscountType.TWO_FOR_ONE_DISCOUNT).isDisplayed()
    }

    @Test
    fun productItem_doesNotShowDiscountInfoWhenProductHasNoDiscount() {
        composeTestRule.activity.setContent {
            productCartViewModel = hiltViewModel()
            ProductItem(
                product = sampleProductWithoutDiscount,
                productCartViewModel = productCartViewModel,
                onSelectProduct = {}
            )
        }
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("DiscountableProduct").assertDoesNotExist()
    }

    @Test
    fun productItem_displaysProductNameAndPriceCorrectly() {
        composeTestRule.activity.setContent {
            productCartViewModel = hiltViewModel()
            ProductItem(
                product = sampleProductWithoutDiscount,
                productCartViewModel = productCartViewModel,
                onSelectProduct = {}
            )
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText(sampleProductWithoutDiscount.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(sampleProductWithoutDiscount.price.toCurrencyFormat())
            .assertIsDisplayed()
    }

    @Test
    fun productItem_callsOnSelectProductOnClick() {
        var isProductSelected = false
        composeTestRule.activity.setContent {
            productCartViewModel = hiltViewModel()
            ProductItem(
                product = sampleProductWithoutDiscount,
                productCartViewModel = productCartViewModel,
                onSelectProduct = { isProductSelected = true }
            )
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("ProductCard").performClick()
        assert(isProductSelected)
    }

    @Test
    fun productItem_showsAddButtonWhenProductNotInCart() {

        composeTestRule.activity.setContent {
            productCartViewModel = hiltViewModel()

            ProductItem(
                product = sampleProductWithoutDiscount,
                productCartViewModel = productCartViewModel,
                onSelectProduct = {}
            )
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.add_item))
            .assertIsDisplayed()
    }
}
