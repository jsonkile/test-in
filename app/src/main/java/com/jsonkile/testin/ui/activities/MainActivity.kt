package com.jsonkile.testin.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestInTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            val homeViewModel = hiltViewModel<HomeViewModel>()
                            HomeComposable(homeUiState = homeViewModel.homeUiState)
                        }

                        composable("movie-details/{id}") {
                            MovieDetailsComposable(
                                movieDetailsUiState = MovieDetailsViewModel.MovieDetailsUiState(),
                                onBackPressed = {})
                        }
                    }

                }
            }
        }
    }
}