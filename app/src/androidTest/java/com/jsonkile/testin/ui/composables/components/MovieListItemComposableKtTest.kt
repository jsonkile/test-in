package com.jsonkile.testin.ui.composables.components

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.jsonkile.testin.ui.models.MovieUILayer
import com.jsonkile.testin.ui.theme.TestInTheme
import org.junit.Rule
import org.junit.Test

class MovieListItemComposableKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testMovieListItem() {
        composeTestRule.setContent {
            TestInTheme {
                Surface {

                    var isItemClicked by remember { mutableStateOf(false) }
                    Text(
                        text = "$isItemClicked",
                        modifier = Modifier.testTag("is_item_clicked_text_test_tag")
                    )

                    MovieListItemComposable(
                        movieUILayer = MovieUILayer(
                            title = "title",
                            imdbID = "id",
                            posterUrl = "posterUrl"
                        ),
                        onItemClickAction = { isItemClicked = true }
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("title").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = movies_list_item_test_tag).assertHasClickAction()
        composeTestRule.onNodeWithTag("is_item_clicked_text_test_tag").assertTextEquals("false")
        composeTestRule.onNodeWithTag(testTag = movies_list_item_test_tag).performClick()
        composeTestRule.onNodeWithTag("is_item_clicked_text_test_tag").assertTextEquals("true")
    }
}