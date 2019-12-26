package br.com.concrete

import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class LoginUseCaseTest {
    private val mockPasswordValidator = mockk<PasswordValidator>()
    private val loginUseCase = LoginUseCase(mockPasswordValidator)

    @Test(expected = PasswordInvalidException::class)
    fun givenInvalidPassword_whenValidate_thenThrowPasswordInvalidException() {
        every { mockPasswordValidator.isValid(any()) } returns false

        loginUseCase.execute("xpto")
    }
}