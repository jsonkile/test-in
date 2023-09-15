package com.jsonkile.testin.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("Error")
    val error: String? = "",
    @SerialName("Response")
    val response: String? = ""
)