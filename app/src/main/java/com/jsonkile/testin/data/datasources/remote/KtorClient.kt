package com.jsonkile.testin.data.datasources.remote

import com.jsonkile.testin.data.models.ErrorResponse
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun <T : HttpClientEngineConfig> HttpClientConfig<T>.build() {
    expectSuccess = true

    HttpResponseValidator {
        handleResponseExceptionWithRequest { exception, _ ->
            val responseException = exception as? ResponseException
                ?: return@handleResponseExceptionWithRequest
            val exceptionResponse = responseException.response

            when (exceptionResponse.status) {
                HttpStatusCode.NotFound -> {
                    throw NotFoundException()
                }

                HttpStatusCode.Unauthorized -> {
                    val error: ErrorResponse = exceptionResponse.body()
                    throw NotAuthorizedException(message = error.error ?: "Something went wrong.")
                }

                else -> {
                    throw GenericException("Something went wrong.")
                }
            }
        }
    }

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }

    install(Logging)

    defaultRequest {
        url {
            protocol = URLProtocol.HTTP
            host = "www.omdbapi.com"
            parameters.append("apiKey", "27e706a2")
        }
        header("Accept", "*/*")
    }
}