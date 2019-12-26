package br.com.concrete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val state = MutableLiveData<LoginViewState>()

    fun getViewState(): LiveData<LoginViewState> = state

    fun checkCredentials(
        password: String
    ) {
        try {
            loginUseCase.execute(password)
            state.value = LoginViewState.LoginSuccessful
        } catch (exception: PasswordInvalidException) {
            state.value = LoginViewState.LoginError(R.string.invalid_password_error)
        }
    }
}
