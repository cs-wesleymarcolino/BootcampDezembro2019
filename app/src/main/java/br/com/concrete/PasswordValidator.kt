package br.com.concrete

class PasswordValidator {
    fun isValid(password: String): Boolean {
        return password.length >= 8
    }
}