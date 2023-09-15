package com.jsonkile.testin.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jsonkile.testin.ui.composables.screens.home.HomeComposable
import com.jsonkile.testin.ui.composables.screens.home.HomeViewModel
import com.jsonkile.testin.ui.composables.screens.moviedetails.MovieDetailsComposable
import com.jsonkile.testin.ui.composables.screens.moviedetails.MovieDetailsViewModel
import com.jsonkile.testin.ui.theme.TestInTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestInTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics {
                            testTagsAsResourceId = true
                        },
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            val homeViewModel = hiltViewModel<HomeViewModel>()

                            homeViewModel.homeUiState.message?.let { message ->
                                LaunchedEffect(message) {
                                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                                        .show()

                                    homeViewModel.clearMessage()
                                }
                            }

                            HomeComposable(
                                homeUiState = homeViewModel.homeUiState,
                                onSearchButtonClick = {
                                    homeViewModel.searchMovies(refresh = true)
                                },
                                onSearchTextChange = { text ->
                                    homeViewModel.updateUiStateSearchKeyword(keyword = text)
                                },
                                onMovieItemClickAction = { movie ->
                                    navController.navigate("movie-details/{${movie.imdbID}}")
                                }
                            )
                        }

                        composable("movie-details/{id}") {
                            val moviesDetailsViewModel = hiltViewModel<MovieDetailsViewModel>()

                            MovieDetailsComposable(
                                movieDetailsUiState = moviesDetailsViewModel.movieDetailsUiState,
                                onBackPressed = { navController.popBackStack() })
                        }
                    }

                }
            }
        }
    }
}