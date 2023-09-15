package com.jsonkile.testin.ui.composables.screens.home

import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.jsonkile.testin.ui.models.MovieUILayer
import com.jsonkile.testin.ui.theme.TestInTheme
import org.junit.Rule
import org.junit.Test

class HomeComposableKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testHomeComposable() {
        composeTestRule.setContent {
            TestInTheme {
                Surface {

                    var homeUiState by remember {
                        mutableStateOf(
                            HomeViewModel.HomeUiState(
                                movies = listOf(
                                    MovieUILayer(title = ""),
                                    MovieUILayer(title = "")
                                )
                            )
                        )
                    }

                    HomeComposable(
                        homeUiState = homeUiState,
                        onSearchTextChange = {
                            homeUiState = homeUiState.copy(searchKeyword = it)
                        },
                        onSearchButtonClick = {
                            homeUiState = homeUiState.copy(isFetchingMovies = true)
                        },
                        onMovieItemClickAction = {}
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag(search_button_test_tag).assertIsNotEnabled()
        composeTestRule.onNodeWithTag(search_text_field_test_tag).performTextInput("input")
        composeTestRule.onNodeWithTag(search_text_field_test_tag).assertTextEquals("input")
        composeTestRule.onNodeWithTag(search_button_test_tag).assertIsEnabled()

        composeTestRule.onNodeWithTag(results_section_test_tag).assertIsDisplayed()
        composeTestRule.onNodeWithTag(search_button_test_tag).performClick()

        composeTestRule.onNodeWithTag(loading_indication_test_tag).assertIsDisplayed()
    }
}