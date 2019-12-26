package br.com.concrete

class PasswordValidator {
    private val validations = listOf(
        "[A-Z]",
        "[a-z]",
        "[0-9]",
        "\\W",
        "^[\\w\\W]{8,16}\$"
    ).map { it.toRegex() }

    fun isValid(password: String) = validations.all { validation ->
        password.contains(validation)
    } && password.isNotEmpty()
}