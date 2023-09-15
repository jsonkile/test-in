package com.jsonkile.testin.ui.composables.components.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jsonkile.testin.ui.composables.screens.home.HomeComposable
import com.jsonkile.testin.ui.composables.screens.home.HomeViewModel
import com.jsonkile.testin.ui.composables.screens.moviedetails.MovieDetailsComposable
import com.jsonkile.testin.ui.composables.screens.moviedetails.MovieDetailsViewModel

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            val homeViewModel = hiltViewModel<HomeViewModel>()

            val context = LocalContext.current

            homeViewModel.homeUiState.message?.let { message ->
                LaunchedEffect(message) {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT)
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
                    navController.navigate("movie-details/${movie.imdbID}")
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