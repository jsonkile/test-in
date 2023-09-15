package com.jsonkile.testin.ui.composables.components.navigation

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.jsonkile.testin.ui.activities.MainActivity
import com.jsonkile.testin.ui.composables.components.movies_list_item_test_tag
import com.jsonkile.testin.ui.composables.screens.home.search_button_test_tag
import com.jsonkile.testin.ui.composables.screens.home.search_text_field_test_tag
import com.jsonkile.testin.ui.composables.screens.moviedetails.back_button_test_tag
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AppNavHostKtTest {


    @get:Rule(order = 0)
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        hiltAndroidRule.inject()

        navController = TestNavHostController(composeTestRule.activity)
        navController.navigatorProvider.addNavigator(ComposeNavigator())

        composeTestRule.activity.setContent {
            AppNavHost(navController = navController)
        }

    }

    @Test
    fun test_appNavHost_verifyStartDestination() {
        composeTestRule.onNodeWithTag(search_button_test_tag).assertIsDisplayed()
    }

    @Test
    fun test_appNavHost_navigatesToMovieDetailsWithImdbID() {
        composeTestRule.onNodeWithTag(search_text_field_test_tag).performTextInput("avengers")
        composeTestRule.onNodeWithTag(search_button_test_tag).performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onAllNodes(hasTestTag(movies_list_item_test_tag)).onFirst().performClick()

        assertEquals(
            true, navController.backStack.last().arguments?.containsKey("id")
        )

        composeTestRule.onNodeWithTag(back_button_test_tag).assertIsDisplayed()
    }
}