package br.com.concrete

import org.junit.Assert
import org.junit.Test

class PasswordValidatorTest {
    private val passwordValidator = PasswordValidator()

    @Test
    fun givenPasswordIsShorterThan8_whenValidate_shouldReturnFalse() {
        val result = passwordValidator.isValid("1aA#123")

        Assert.assertFalse(result)
    }

    @Test
    fun givenPasswordDoesntHaveAnUppercaseLetter_whenValidate_shouldReturnFalse() {
        val result = passwordValidator.isValid("1aa#1234")

        Assert.assertFalse(result)
    }

    @Test
    fun givenPasswordDoesntHaveALowercaseLetter_whenValidate_shouldReturnFalse() {
        val result = passwordValidator.isValid("1AA#1235")

        Assert.assertFalse(result)
    }

    @Test
    fun givenPasswordDoesntHaveANumber_whenValidate_shouldReturnFalse() {
        val result = passwordValidator.isValid("aaA#bcde")

        Assert.assertFalse(result)
    }

    @Test
    fun givenPasswordDoesntHaveASpecialCharacter_whenValidate_shouldReturnFalse() {
        val result = passwordValidator.isValid("1aAa1235")

        Assert.assertFalse(result)
    }

    @Test
    fun givenPasswordIsLongerThan16_whenValidate_shouldReturnFalse() {
        val result = passwordValidator.isValid("1aA#12351aA#12351aA#12351aA#12351a")

        Assert.assertFalse(result)
    }

    @Test
    fun givenPasswordEmpty_whenValidate_shouldReturnFalse() {
        val result = passwordValidator.isValid("")

        Assert.assertFalse(result)
    }

    @Test
    fun givenPasswordIsValid_whenValidate_shouldReturnTrue() {
        val result = passwordValidator.isValid("1aA#d1235")

        Assert.assertTrue(result)
    }
}