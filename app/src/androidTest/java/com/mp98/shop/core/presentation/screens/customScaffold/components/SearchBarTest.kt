package com.mp98.shop.core.presentation.screens.customScaffold.components

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.hilt.navigation.compose.hiltViewModel
import com.mp98.shop.MainActivity
import com.mp98.shop.R
import com.mp98.shop.core.presentation.screens.customscaffold.components.SearchBar
import com.mp98.shop.core.presentation.viewmodels.ProductCartViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SearchBarTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var productCartViewModel: ProductCartViewModel

    @Before
    fun setUp() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            productCartViewModel = hiltViewModel()
            SearchBar(productCartViewModel = productCartViewModel)
        }

        composeTestRule.waitForIdle()
    }

    @Test
    fun searchBar_displaysPlaceholderText() {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.search))
            .assertIsDisplayed()
    }

    @Test
    fun searchBar_updatesTextOnTyping() {
        val searchText = "Test search"

        composeTestRule.onNode(hasSetTextAction())
            .performTextInput(searchText)

        assertThat(productCartViewModel.productsCartState.value.searchText, `is`(searchText))
    }
}