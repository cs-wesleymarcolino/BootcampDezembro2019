package br.com.concrete

import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @get:Rule
    val activityRule = IntentsTestRule(LoginActivity::class.java)

    @Test
    fun givenInitialState_shouldHaveEmptyEmailAndPassword() {
        loginAssert {
            checkEmailIsEmpty()
            checkPasswordIsEmpty()
        }
    }

    @Test
    fun givenEmailIsEmpty_whenLogin_shouldShowEmailIsEmptyError() {
        loginAct {
            typePassword("aA#1mamda")
            clickLogin()
        }

        loginAssert {
            checkMessageIsShown(R.string.email_is_empty_error)
        }
    }

    @Test
    fun givenPasswordIsInvalid_whenLogin_shouldShowInvalidPasswordError() {
        loginAct {
            typeValidEmail()
            typePassword("aaaaAAAA")

            clickLogin()
        }

        loginAssert {
            checkMessageIsShown(R.string.invalid_password_error)
        }
    }

    @Test
    fun givenEmailAndPasswordAreValid_whenLogin_shouldGoToHomeScreen() {
        loginArrange {
            mockHomeActivityIntent()
        }

        loginAct {
            typeValidEmail()
            typePassword("aA#1dkjfjk")

            clickLogin()
        }

        loginAssert {
            checkHomeActivityWasCalled()
        }
    }
}