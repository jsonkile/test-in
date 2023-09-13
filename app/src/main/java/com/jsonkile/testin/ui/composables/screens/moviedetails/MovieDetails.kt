package com.jsonkile.testin.ui.composables.screens.moviedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jsonkile.testin.ui.models.MovieDetails
import com.jsonkile.testin.ui.theme.TestInTheme

@Composable
fun MovieDetailsComposable(
    movieDetailsUiState: MovieDetailsViewModel.MovieDetailsUiState,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = onBackPressed, modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(corner = CornerSize(0.dp))
        ) {
            Icon(imageVector = Icons.Outlined.KeyboardArrowLeft, contentDescription = null)
            Text(text = "Back")
        }

        Spacer(modifier = Modifier.height(15.dp))

        movieDetailsUiState.movieDetails?.let { details ->

            AsyncImage(
                model = details.posterUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(fraction = .8F)
                    .aspectRatio(3 / 4F)
                    .background(
                        shape = RoundedCornerShape(0.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = details.title,
                style = MaterialTheme.typography.labelLarge,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Director: ${details.director}",
                style = MaterialTheme.typography.labelMedium,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Cast: ${details.cast}",
                style = MaterialTheme.typography.labelMedium,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Release date: ${details.releaseDate}",
                style = MaterialTheme.typography.labelMedium,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Plot: ${details.plot}",
                style = MaterialTheme.typography.labelMedium,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )

        }

    }
}

@Preview
@Composable
fun MovieDetailsComposablePreview() {
    TestInTheme {
        Surface {
            MovieDetailsComposable(
                movieDetailsUiState = MovieDetailsViewModel.MovieDetailsUiState(
                    movieDetails = MovieDetails(
                        title = "The Avengers",
                        director = "Micheal Scott",
                        cast = "Favour Odi, Mary White, Promise Rick",
                        releaseDate = "16 Feb 1996",
                        plot = "A rejected hockey player puts his skills to the golf course to save his grandmother's house."
                    )
                ),
                onBackPressed = {}
            )
        }
    }
}