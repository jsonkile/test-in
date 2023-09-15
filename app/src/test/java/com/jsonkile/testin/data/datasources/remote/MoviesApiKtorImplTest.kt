package com.jsonkile.testin.data.datasources.remote

import com.jsonkile.testin.data.models.MoviesSearchApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.request
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class MoviesApiKtorImplTest {

    @Test
    fun `test that call returns correct response value from result when success`() {
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

        runBlocking {
            assertEquals(
                "True",
                client.get("http://www.omdbapi.com").body<MoviesSearchApiResponse>().response
            )
        }
    }

    @Test
    fun `test that call returns correct number of items from result when success`() {
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

        runBlocking {
            assertEquals(
                10,
                client.get("http://www.omdbapi.com").body<MoviesSearchApiResponse>().search?.size
            )
        }
    }

    @Test
    fun `test that call returns correct non authorized exception when unauthorized`() {
        val mockEngine = MockEngine { _ ->
            respond(
                content = ByteReadChannel("""{"Response":"False","Error":"Invalid API key!"}"""),
                status = HttpStatusCode.Unauthorized,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClient(mockEngine) {
            build()
        }

        assertThrows(NotAuthorizedException::class.java) {
            runBlocking {
                client.get("http://www.omdbapi.com").body()
            }
        }
    }

    @Test
    fun `test that call returns generic exception when unauthorized`() {
        val mockEngine = MockEngine { _ ->
            respondError(
                content = "Bad Gateway",
                status = HttpStatusCode.BadGateway,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClient(mockEngine) {
            build()
        }

        assertThrows(GenericException::class.java) {
            runBlocking {
                client.get("http://www.omdbapi.com").body()
            }
        }
    }

    @Test
    fun `test that correct endpoint is called`() {
        val mockEngine = MockEngine { _ ->
            respond(
                content = "Success",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClient(mockEngine) {
            build()
        }

        runBlocking {
            assertEquals("http://www.omdbapi.com?s=test&apiKey=27e706a2", client.get {
                parameter("s", "test")
            }.request.url.toString())
        }
    }
}