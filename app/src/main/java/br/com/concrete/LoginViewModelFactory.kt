package br.com.concrete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class LoginViewModelFactory(private val loginUseCase: LoginUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((LoginViewModel::class.java))) {
            return LoginViewModel(loginUseCase) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
