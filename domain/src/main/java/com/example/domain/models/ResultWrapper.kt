package com.example.domain.models

sealed class ResultWrapper<out R> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: String? = null): ResultWrapper<Nothing>()
    object NetworkError: ResultWrapper<Nothing>()
}