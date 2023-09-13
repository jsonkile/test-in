package com.jsonkile.testin.data.datasources.remote

abstract class BaseClientException(message: String) : Exception(message)

class GenericException(message: String) : BaseClientException(message)

class NotFoundException : BaseClientException("The resource was not found!")

class NotAuthorizedException(message: String) : BaseClientException(message)