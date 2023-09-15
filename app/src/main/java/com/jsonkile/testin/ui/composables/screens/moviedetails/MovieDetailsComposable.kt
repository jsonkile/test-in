package com.jsonkile.testin.ui.composables.screens.moviedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jsonkile.testin.ui.models.MovieDetailsUILayer
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
            onClick = onBackPressed,
            modifier = Modifier
                .wrapContentSize()
                .semantics { testTag = back_button_test_tag },
            shape = RoundedCornerShape(corner = CornerSize(0.dp))
        ) {
            Icon(imageVector = Icons.Outlined.KeyboardArrowLeft, contentDescription = null)
            Text(text = "Back")
        }

        Spacer(modifier = Modifier.height(15.dp))

        if (movieDetailsUiState.movieDetailsUILayer != null && movieDetailsUiState.isFetchingDetails.not()) {
            AsyncImage(
                model = movieDetailsUiState.movieDetailsUILayer.posterUrl,
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
                text = movieDetailsUiState.movieDetailsUILayer.title,
                style = MaterialTheme.typography.labelLarge,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.semantics { testTag = title_text_test_tag }
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Director: ${movieDetailsUiState.movieDetailsUILayer.director}",
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.semantics { testTag = director_text_test_tag }
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Writer: ${movieDetailsUiState.movieDetailsUILayer.writer}",
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.semantics { testTag = writer_text_test_tag }
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Cast: ${movieDetailsUiState.movieDetailsUILayer.cast}",
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Release date: ${movieDetailsUiState.movieDetailsUILayer.releaseDate}",
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Plot: ${movieDetailsUiState.movieDetailsUILayer.plot}",
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Running time: ${movieDetailsUiState.movieDetailsUILayer.runtime}",
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Genre: ${movieDetailsUiState.movieDetailsUILayer.genre}",
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )

        }

        if (movieDetailsUiState.isFetchingDetails) {
            Spacer(modifier = Modifier.height(45.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                CircularProgressIndicator(modifier = Modifier
                    .align(Alignment.Center)
                    .semantics { testTag = loading_indication_test_tag })
            }
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
                    movieDetailsUILayer = MovieDetailsUILayer(
                        title = "The Avengers",
                        director = "Micheal Scott",
                        cast = "Favour Odi, Mary White, Promise Rick",
                        releaseDate = "16 Feb 1996",
                        plot = "A rejected hockey player puts his skills to the golf course to save his grandmother's house.",
                        writer = "James Brown",
                        runtime = "124 mins",
                        genre = "Thriller"
                    ),
                    isFetchingDetails = false
                ),
                onBackPressed = {}
            )
        }
    }
}

const val back_button_test_tag = "back_button_test_tag"
const val title_text_test_tag = "title_text_test_tag"
const val director_text_test_tag = "director_text_test_tag"
const val writer_text_test_tag = "writer_text_test_tag"
const val loading_indication_test_tag = "loading_indication_test_tag"