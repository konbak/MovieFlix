package app.example.network

import app.example.domain.shared.DomainError
import okio.IOException
import retrofit2.HttpException

fun Throwable.toDomainError(): DomainError = when (this) {
    is IOException -> DomainError.Network
    is HttpException -> DomainError.Server
    else -> DomainError.Unknown(this.message)
}