package com.example.photoshow.data

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.example.domain.Error
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toError(): Error = when (this) {
    is IOException -> Error.Connectivity
    is HttpException -> Error.Server(code())
    else -> Error.Unknown(message ?: "")
}

suspend fun <T> tryCall(action: suspend () -> T): Either<Error, T> = try {
    action().right()
} catch (e: Exception) {
    e.toError().left()
}
