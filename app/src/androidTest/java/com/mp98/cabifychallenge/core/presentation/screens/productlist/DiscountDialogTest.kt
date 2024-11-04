package com.mp98.cabifychallenge.core.presentation.screens.productlist

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.filters.LargeTest
import com.mp98.cabifychallenge.MainActivity
import com.mp98.cabifychallenge.R
import com.mp98.cabifychallenge.core.domain.cart.discount.DiscountType
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@LargeTest
@HiltAndroidTest
class DiscountDialogTest {

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
            DiscountDialog(productCartViewModel = viewModel)
        }
        composeTestRule.waitForIdle()
    }

    @Test
    fun discountDialog_DisplaysCorrectlyFor2x1Discount() {

        viewModel.changeShowDiscountDialog(DiscountType.TWO_FOR_ONE_DISCOUNT)

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("DiscountDialog").assertIsDisplayed()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.discount2x1)).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.discount2x1)).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.accept)).performClick()

        composeTestRule.onNodeWithTag("DiscountDialog").assertDoesNotExist()
    }

    @Test
    fun discountDialog_DisplaysCorrectlyForBulkDiscount() {
        viewModel.changeShowDiscountDialog(DiscountType.BULK_DISCOUNT)

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("DiscountDialog").assertIsDisplayed()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.discountBulk)).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.discountBulk)).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.accept)).performClick()

        composeTestRule.onNodeWithTag("DiscountDialog").assertDoesNotExist()
    }
}