package br.com.concrete

class LoginUseCase(private val passwordValidator: PasswordValidator) {
    fun execute(
        password: String
    ) {
        if (!passwordValidator.isValid(password)) throw PasswordInvalidException()
    }
}
