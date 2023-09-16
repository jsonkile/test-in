package com.jsonkile.testin.ui.composables.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jsonkile.testin.ui.composables.components.MovieListItemComposable
import com.jsonkile.testin.ui.models.MovieUILayer
import com.jsonkile.testin.ui.theme.TestInTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeComposable(
    homeUiState: HomeViewModel.HomeUiState,
    onSearchTextChange: (String) -> Unit,
    onSearchButtonClick: () -> Unit,
    onMovieItemClickAction: (MovieUILayer) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp),
    ) {
        val (searchTextField, searchButton, resultsList, loadingIndicator) = createRefs()

        TextField(
            value = homeUiState.searchKeyword,
            onValueChange = onSearchTextChange,
            modifier = Modifier
                .constrainAs(searchTextField) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(searchButton.start)
                }
                .semantics { testTag = search_text_field_test_tag },
            shape = RoundedCornerShape(corner = CornerSize(0.dp)),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            )
        )

        val enableSearchButton =
            remember(homeUiState.searchKeyword) { homeUiState.searchKeyword.isNotEmpty() }

        Button(
            onClick = onSearchButtonClick, modifier = Modifier
                .constrainAs(searchButton) {
                    top.linkTo(searchTextField.top)
                    bottom.linkTo(searchTextField.bottom)
                    end.linkTo(parent.end)

                    height = Dimension.fillToConstraints
                }
                .semantics { testTag = search_button_test_tag },
            shape = RoundedCornerShape(corner = CornerSize(0.dp)),
            enabled = enableSearchButton
        ) {
            Text(text = "Search")
        }

        if (homeUiState.isFetchingMovies) {
            CircularProgressIndicator(modifier = Modifier
                .constrainAs(loadingIndicator) {
                    top.linkTo(searchTextField.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .semantics { testTag = loading_indication_test_tag })
        }


        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier
                .constrainAs(resultsList) {
                    top.linkTo(searchTextField.bottom)
                    bottom.linkTo(parent.bottom)

                    height = Dimension.fillToConstraints
                }
                .semantics { testTag = results_section_test_tag },
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            contentPadding = PaddingValues(bottom = 70.dp, top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(homeUiState.movies) { movie ->
                MovieListItemComposable(
                    movieUILayer = movie,
                    onItemClickAction = onMovieItemClickAction
                )
            }
        }

    }
}

@Preview
@Composable
fun HomeComposablePreview() {
    TestInTheme {
        Surface {
            HomeComposable(
                homeUiState = HomeViewModel.HomeUiState(
                    movies = listOf(
                        MovieUILayer(title = "Avengers"),
                        MovieUILayer(title = "Mission Impossible"),
                        MovieUILayer(title = "The Help")
                    ),
                    isFetchingMovies = false,
                    searchKeyword = "Avengers"
                ),
                onSearchButtonClick = {},
                onSearchTextChange = {},
                onMovieItemClickAction = {}
            )
        }
    }
}


const val search_text_field_test_tag = "search_text_field_test_tag"
const val search_button_test_tag = "search_button_test_tag"
const val results_section_test_tag = "results_section_test_tag"
const val loading_indication_test_tag = "loading_indication_test_tag"