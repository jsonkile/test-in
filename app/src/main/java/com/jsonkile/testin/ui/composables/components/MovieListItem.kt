package com.jsonkile.testin.ui.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jsonkile.testin.ui.models.Movie
import com.jsonkile.testin.ui.theme.TestInTheme

@Composable
fun MovieListItemComposable(movie: Movie) {
    Column(modifier = Modifier.wrapContentSize()) {
        AsyncImage(
            model = movie.posterUrl,
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(3 / 4F)
                .background(
                    shape = RoundedCornerShape(0.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = movie.title,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
fun MovieListItemComposablePreview() {
    TestInTheme {
        Surface {
            MovieListItemComposable(
                movie = Movie(
                    id = "",
                    title = "The Avengers",
                    posterUrl = ""
                )
            )
        }
    }
}