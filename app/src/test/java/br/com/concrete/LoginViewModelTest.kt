package br.com.concrete

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockLoginUseCase = mockk<LoginUseCase>()
    private val loginViewModel = LoginViewModel(mockLoginUseCase)

    @Test
    fun givenInvalidPasswordException_whenValidatingCredentials_shouldNotifyPasswordErrorState() {
        every { mockLoginUseCase.execute(any()) } throws PasswordInvalidException()

        loginViewModel.checkCredentials("xpto")

        assertEquals(
            loginViewModel.getViewState().value,
            LoginViewState.LoginError(R.string.invalid_password_error)
        )
    }

    @Test
    fun givenValidPassword_whenValidatingCredentials_shouldNotifyLoginSuccessful() {
        every { mockLoginUseCase.execute(any()) } just runs

        loginViewModel.checkCredentials("xpto")

        assertEquals(
            loginViewModel.getViewState().value,
            LoginViewState.LoginSuccessful
        )
    }
}