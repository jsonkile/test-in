package com.jsonkile.testin.data.datasources.remote

import com.jsonkile.testin.data.models.MovieDataLayer
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class MoviesApiKtorImplTest {

    @Test
    fun `test search movies`() = runTest {
        val mockEngine = MockEngine { _ ->
            respond(
                content = ByteReadChannel(
                    """{"Search":[
                            |{"Title":"Happy Gilmore","Year":"1996","imdbID":"tt0116483","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BZWI2NjliOTYtZjE1OS00YzAyLWJjYTQtYWNmZTQzMTQzNzVjXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"},
                            |{"Title":"Happy Feet","Year":"2006","imdbID":"tt0366548","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BZWU2NDkxYjktNWVlMS00MTM4LWJjMDAtOWYxZjJkZWFhYzAxXkEyXkFqcGdeQXVyMTA1NjE5MTAz._V1_SX300.jpg"},
                            |{"Title":"Happy Death Day","Year":"2017","imdbID":"tt5308322","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BY2M3MTM4ZTMtNjg0ZC00ZDVkLTkxNWQtNThkZGZkYTQ1MjAyXkEyXkFqcGdeQXVyNjc5NjEzNA@@._V1_SX300.jpg"},
                            |{"Title":"Happy Death Day 2U","Year":"2019","imdbID":"tt8155288","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BMTg0NzkwMzQyMV5BMl5BanBnXkFtZTgwNDcxMTMyNzM@._V1_SX300.jpg"},
                            |{"Title":"Happy Valley","Year":"2014–2023","imdbID":"tt3428912","Type":"series","Poster":"https://m.media-amazon.com/images/M/MV5BNTBmYTQ5ZDYtM2NjMC00M2Q2LTg4NDAtODUxZTk0ZmFjMGVjXkEyXkFqcGdeQXVyNzQ5MzY0NjM@._V1_SX300.jpg"},
                            |{"Title":"Happy Feet Two","Year":"2011","imdbID":"tt1402488","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BMTg1MzU2Nzg2OV5BMl5BanBnXkFtZTcwNzE3MzAxNg@@._V1_SX300.jpg"},
                            |{"Title":"Happy!","Year":"2017–2019","imdbID":"tt2452242","Type":"series","Poster":"https://m.media-amazon.com/images/M/MV5BNzk2NjYxMDMxMV5BMl5BanBnXkFtZTgwMzQxODcwNDI@._V1_SX300.jpg"},
                            |{"Title":"Happy-Go-Lucky","Year":"2008","imdbID":"tt1045670","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BMTI4ODY1MjIyNV5BMl5BanBnXkFtZTcwMTExMTM5MQ@@._V1_SX300.jpg"},
                            |{"Title":"Happy Endings","Year":"2011–2020","imdbID":"tt1587678","Type":"series","Poster":"https://m.media-amazon.com/images/M/MV5BMjE0NTQyOTM5OF5BMl5BanBnXkFtZTcwOTYyMTU2OA@@._V1_SX300.jpg"},
                            |{"Title":"Happy New Year","Year":"2014","imdbID":"tt2461132","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BMjEwZGQwZWMtNTU4Zi00YWVlLTlhZTYtZjc2MzJmMGVmMDQwXkEyXkFqcGdeQXVyNjQ3ODkxMjE@._V1_SX300.jpg"}],"totalResults":"2816","Response":"True"}""".trimMargin()
                ),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClient(mockEngine) {
            build()
        }

        val moviesApiKtorImpl = MoviesApiKtorImpl(client)

        val result = moviesApiKtorImpl.searchMovies("keyword")

        Assert.assertEquals(
            MovieDataLayer(
                title = "Happy Gilmore",
                imdbID = "tt0116483",
                keyword = "keyword",
                poster = "https://m.media-amazon.com/images/M/MV5BZWI2NjliOTYtZjE1OS00YzAyLWJjYTQtYWNmZTQzMTQzNzVjXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"
            ),
            result.first()
        )
    }

    @Test
    fun `test get movie`() = runTest {
        val mockEngine = MockEngine { _ ->
            respond(
                content = ByteReadChannel(
                    """{"Title":"Happy Gilmore","Year":"1996","Rated":"PG-13","Released":"16 Feb 1996","Runtime":"92 min","Genre":"Comedy, Sport","Director":"Dennis Dugan","Writer":"Tim Herlihy, Adam Sandler","Actors":"Adam Sandler, Christopher McDonald, Julie Bowen","Plot":"A rejected hockey player puts his skills to the golf course to save his grandmother's house.","Language":"English","Country":"United States","Awards":"1 win & 4 nominations","Poster":"https://m.media-amazon.com/images/M/MV5BZWI2NjliOTYtZjE1OS00YzAyLWJjYTQtYWNmZTQzMTQzNzVjXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg","Ratings":[{"Source":"Internet Movie Database","Value":"7.0/10"},{"Source":"Rotten Tomatoes","Value":"62%"},{"Source":"Metacritic","Value":"31/100"}],"Metascore":"31","imdbRating":"7.0","imdbVotes":"239,710","imdbID":"tt0116483","Type":"movie","DVD":"15 Jun 2012","BoxOffice":"${'$'}38,824,099","Production":"N/A","Website":"N/A","Response":"True"}""".trimMargin()
                ),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClient(mockEngine) {
            build()
        }

        val moviesApiKtorImpl = MoviesApiKtorImpl(client)

        val result = moviesApiKtorImpl.getMovie("")

        Assert.assertEquals(
            "Happy Gilmore",
            result.title
        )

        Assert.assertEquals(
            "16 Feb 1996",
            result.released
        )
    }
}