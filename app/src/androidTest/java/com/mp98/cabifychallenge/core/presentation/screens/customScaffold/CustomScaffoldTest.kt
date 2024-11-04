package com.mp98.cabifychallenge.core.presentation.screens.customScaffold

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.core.app.ActivityScenario
import com.mp98.cabifychallenge.MainActivity
import com.mp98.cabifychallenge.core.presentation.screens.customscaffold.CustomScaffold
import com.mp98.cabifychallenge.core.presentation.screens.navigation.NavigationRoute
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CustomScaffoldTest {

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
            CustomScaffold(
                modifier = Modifier.fillMaxSize(),
                productCartViewModel = productCartViewModel,
                content = {
                    // Aquí puedes agregar contenido de ejemplo si es necesario
                    Text("Content")
                },
                onChangeToCart = { productCartViewModel.changeScreen(NavigationRoute.CartListScreen) },
                onChangeToProducts = { productCartViewModel.changeScreen(NavigationRoute.ProductListScreen) }
            )
        }

        composeTestRule.waitForIdle()
    }

    @Test
    fun customScaffold_showsTopBarAndBottomBarInCartScreen() {
        composeTestRule.runOnUiThread {
            productCartViewModel.changeScreen(NavigationRoute.CartListScreen)
        }
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("TopBar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("BottomBar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("FloatingActionButton").assertIsDisplayed()
    }

    @Test
    fun customScaffold_showsOnlyTopBarInProductScreen() {
        composeTestRule.runOnUiThread {
            productCartViewModel.changeScreen(NavigationRoute.ProductListScreen)
        }
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("TopBar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("BottomBar").assertDoesNotExist()
        composeTestRule.onNodeWithTag("FloatingActionButton").assertDoesNotExist()
    }

    @Test
    fun backPressHandler_navigatesBackFromCartScreen() {
        composeTestRule.runOnUiThread {
            productCartViewModel.changeScreen(NavigationRoute.CartListScreen)
        }
        composeTestRule.waitForIdle()

        composeTestRule.activity.onBackPressedDispatcher.onBackPressed()
        composeTestRule.waitForIdle()

        assert(productCartViewModel.productsCartState.value.screen == NavigationRoute.ProductListScreen)
    }

    @Test
    fun backPressHandler_exitsAppFromProductScreen() {
        composeTestRule.runOnUiThread {
            productCartViewModel.changeScreen(NavigationRoute.ProductListScreen)
        }
        composeTestRule.waitForIdle()

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            activity.onBackPressedDispatcher.onBackPressed()
            assert(activity.isFinishing)
        }
    }
}