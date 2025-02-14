package com.mp98.shop.core.presentation.screens.components

import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import com.mp98.shop.MainActivity
import com.mp98.shop.core.domain.model.Product
import com.mp98.shop.core.presentation.viewmodels.ProductCartViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AddOrTakeOutButtonTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var productCartViewModel: ProductCartViewModel

    private val voucher = Product(code = "VOUCHER", name = "Test Product", price = 5.0)

    @Before
    fun setUp() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            productCartViewModel = hiltViewModel()
            AddOrTakeOutButton(
                product = voucher,
                productCartViewModel = productCartViewModel
            )
        }
        composeTestRule.waitForIdle()
    }

    @Test
    fun addOrTakeOutButton_addProductIncrementsCount() {
        composeTestRule.runOnUiThread {
            productCartViewModel.addProductToCart(voucher)
        }

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText("1").fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText("1").assertExists()

        composeTestRule.runOnUiThread {
            productCartViewModel.removeProductFromCart(voucher)
        }
    }

    @Test
    fun addOrTakeOutButton_removeProductDecrementsCount() {
        composeTestRule.runOnUiThread {
            productCartViewModel.addProductToCart(voucher)
            productCartViewModel.addProductToCart(voucher)
        }

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText("2").fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText("2").assertExists()

        composeTestRule.runOnUiThread {
            productCartViewModel.removeProductFromCart(voucher)
        }

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText("1").fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText("1").assertExists()

        composeTestRule.runOnUiThread {
            productCartViewModel.removeProductFromCart(voucher)
        }
    }

    @Test
    fun addOrTakeOutButton_addAndRemoveProductCorrectlyUpdatesCount() {
        composeTestRule.runOnUiThread {
            productCartViewModel.addProductToCart(voucher)
        }

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText("1").fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText("1").assertExists()

        composeTestRule.runOnUiThread {
            productCartViewModel.removeProductFromCart(voucher)
        }

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText("0").fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText("0").assertExists()
    }

    @Test
    fun addOrTakeOutButton_showsCorrectIconBasedOnCount() {
        composeTestRule.runOnUiThread {
            productCartViewModel.addProductToCart(voucher)
        }

        composeTestRule.waitUntil {
            composeTestRule.onNodeWithContentDescription(Icons.Rounded.Delete.name).isDisplayed()
        }

        composeTestRule.onNodeWithContentDescription(Icons.Rounded.Delete.name).assertExists()

        composeTestRule.runOnUiThread {
            productCartViewModel.addProductToCart(voucher)
        }

        composeTestRule.waitUntil {
            composeTestRule.onNodeWithContentDescription(Icons.Rounded.Remove.name).isDisplayed()
        }

        composeTestRule.onNodeWithContentDescription(Icons.Rounded.Remove.name).assertExists()

        composeTestRule.runOnUiThread {
            productCartViewModel.removeProductFromCart(voucher)
            productCartViewModel.removeProductFromCart(voucher)
        }
    }
}
