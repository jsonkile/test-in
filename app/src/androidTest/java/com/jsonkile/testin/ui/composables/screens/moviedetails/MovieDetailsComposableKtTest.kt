package com.jsonkile.testin.ui.composables.screens.moviedetails

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.jsonkile.testin.ui.models.MovieDetailsUILayer
import com.jsonkile.testin.ui.theme.TestInTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MovieDetailsComposableKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testMoviesDetailsComposable() {

        val movieDetailsUiState = MovieDetailsViewModel.MovieDetailsUiState(
            movieDetailsUILayer = MovieDetailsUILayer(
                title = "title",
                director = "director",
                writer = "writer"
            )
        )

        composeTestRule.setContent {
            TestInTheme {
                Surface {

                    var isBackButtonClicked by remember { mutableStateOf(false) }
                    Text(
                        text = "$isBackButtonClicked",
                        modifier = Modifier.testTag("is_back_button_clicked_text_test_tag")
                    )

                    MovieDetailsComposable(
                        movieDetailsUiState = movieDetailsUiState,
                        onBackPressed = { isBackButtonClicked = true })
                }
            }
        }

        composeTestRule.onNodeWithTag(back_button_test_tag).performClick()
        composeTestRule.onNodeWithTag("is_back_button_clicked_text_test_tag")
            .assertTextEquals("true")

        composeTestRule.onNodeWithTag(title_text_test_tag)
            .assertTextEquals(movieDetailsUiState.movieDetailsUILayer?.title.orEmpty())

        composeTestRule.onNodeWithTag(director_text_test_tag)
            .assertTextEquals("Director: ${movieDetailsUiState.movieDetailsUILayer?.director.orEmpty()}")

        composeTestRule.onNodeWithTag(writer_text_test_tag)
            .assertTextEquals("Writer: ${movieDetailsUiState.movieDetailsUILayer?.writer.orEmpty()}")

        composeTestRule.onNodeWithTag(loading_indication_test_tag).assertDoesNotExist()
    }

    @Test
    fun testMoviesDetailsComposable_ShowsLoadingIndicator_WhenFetchingMovieDetails() {
        composeTestRule.setContent {
            TestInTheme {
                Surface {
                    MovieDetailsComposable(
                        movieDetailsUiState = MovieDetailsViewModel.MovieDetailsUiState(
                            isFetchingDetails = true
                        ),
                        onBackPressed = { })
                }
            }
        }

        composeTestRule.onNodeWithTag(loading_indication_test_tag).assertIsDisplayed()
    }
}