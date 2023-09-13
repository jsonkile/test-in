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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jsonkile.testin.ui.composables.components.MovieListItemComposable
import com.jsonkile.testin.ui.models.Movie
import com.jsonkile.testin.ui.theme.TestInTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeComposable(homeUiState: HomeViewModel.HomeUiState) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        val (searchTextField, searchButton, resultsList, loadingIndicator) = createRefs()
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.constrainAs(searchTextField) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(searchButton.start)
            },
            shape = RoundedCornerShape(corner = CornerSize(0.dp))
        )

        Button(
            onClick = { }, modifier = Modifier.constrainAs(searchButton) {
                top.linkTo(searchTextField.top)
                bottom.linkTo(searchTextField.bottom)
                end.linkTo(parent.end)

                height = Dimension.fillToConstraints
            },
            shape = RoundedCornerShape(corner = CornerSize(0.dp))
        ) {
            Text(text = "Search")
        }

        if (homeUiState.isFetchingMovies) {
            CircularProgressIndicator(modifier = Modifier.constrainAs(loadingIndicator) {
                top.linkTo(searchTextField.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        }

        if (homeUiState.showResultsSection) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                modifier = Modifier.constrainAs(resultsList) {
                    top.linkTo(searchTextField.bottom)
                    bottom.linkTo(parent.bottom)

                    height = Dimension.fillToConstraints
                },
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                contentPadding = PaddingValues(vertical = 18.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(homeUiState.movies) { movie ->
                    MovieListItemComposable(movie = movie)
                }
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
                        Movie(title = "Avengers"),
                        Movie(title = "Mission Impossible"),
                        Movie(title = "The Help")
                    ),
                    isFetchingMovies = true
                )
            )
        }
    }
}