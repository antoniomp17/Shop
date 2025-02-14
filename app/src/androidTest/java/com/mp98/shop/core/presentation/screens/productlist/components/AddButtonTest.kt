package com.mp98.shop.core.presentation.screens.productlist.components

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.filters.LargeTest
import com.mp98.shop.MainActivity
import com.mp98.shop.R
import com.mp98.shop.core.domain.model.Product
import com.mp98.shop.core.presentation.viewmodels.ProductCartViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@LargeTest
@HiltAndroidTest
class AddButtonTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var viewModel: ProductCartViewModel

    @Before
    fun setUp() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            viewModel = hiltViewModel<ProductCartViewModel>()

            val product = Product(code = "VOUCHER",
                name = "Test Product", price = 10.0, discount = null)

            AddButton(product = product, productCartViewModel = viewModel)
        }
        composeTestRule.waitForIdle()
    }

    @Test
    fun addButton_isDisplayedCorrectly() {
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.add_item)
        ).assertIsDisplayed()
    }
}