package com.mp98.cabifychallenge.core.presentation.screens.productlist.components

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.filters.LargeTest
import com.mp98.cabifychallenge.MainActivity
import com.mp98.cabifychallenge.R
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

    private val sampleProduct = Product(code=VOUCHER, name="Cabify Voucher", price=5.0)

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

        composeTestRule.waitUntil{
            composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.add_item)).isDisplayed()
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.add_item)).assertIsDisplayed()
    }
}