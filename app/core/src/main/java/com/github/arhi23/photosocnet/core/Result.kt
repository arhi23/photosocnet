package com.github.arhi23.photosocnet.core

sealed interface Result<T> {
  data class Success<T>(val data: T) : Result<T>
  data class Failure<T>(val data: RepositoryError, val error: Error? = null) : Result<T>
}

sealed interface ServerError
sealed interface RepositoryError {
  object NoInternet : RepositoryError
  object ServerFailure : RepositoryError, ServerError
  class SpecificError(val reason: String = "") : RepositoryError
  class Forbidden(val reason: String = "") : RepositoryError, ServerError
  class ServiceUnavailable(val reason: String = "") : RepositoryError, ServerError
  class InternalServerError(val reason: String = "") : RepositoryError, ServerError
  class BadRequest(val reason: String = "") : RepositoryError, ServerError
  class NotFound(val reason: String = "") : RepositoryError, ServerError
  class Unauthorized(val reason: String = "") : RepositoryError, ServerError
  class LargePayload(val reason: String = "") : RepositoryError, ServerError
}