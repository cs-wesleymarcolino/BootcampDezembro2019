package br.com.concrete

sealed class LoginViewState {
    data class LoginError(val message: Int) : LoginViewState()
    object LoginSuccessful : LoginViewState()
}
