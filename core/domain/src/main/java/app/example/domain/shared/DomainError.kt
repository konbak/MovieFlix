package app.example.domain.shared

sealed class DomainError {
    object Network : DomainError()
    object Server : DomainError()
    data class Unknown(val message: String?) : DomainError()
}