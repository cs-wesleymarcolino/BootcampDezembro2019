package br.com.concrete

import android.app.Activity
import android.app.Instrumentation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
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
        onView(withId(R.id.email))
            .check(matches(withText("")))

        onView(withId(R.id.password))
            .check(matches(withText("")))
    }

    @Test
    fun givenEmailIsEmpty_whenLogin_shouldShowEmailIsEmptyError() {
        onView(withId(R.id.password))
            .perform(typeText("aA#1mamda"))

        onView(withId(R.id.login_button))
            .perform(click())

        onView(withText(R.string.email_is_empty_error))
            .check(matches(isDisplayed()))
    }

    @Test
    fun givenPasswordIsEmpty_whenLogin_shouldShowPasswordIsEmptyError() {
        onView(withId(R.id.email))
            .perform(typeText("w.jonathan.marcolino@accenture.com"))

        onView(withId(R.id.login_button))
            .perform(click())

        onView(withText(R.string.password_is_empty_error))
            .check(matches(isDisplayed()))
    }

    @Test
    fun givenPasswordIsInvalid_whenLogin_shouldShowInvalidPasswordError() {
        onView(withId(R.id.email))
            .perform(typeText("w.jonathan.marcolino@accenture.com"))
        onView(withId(R.id.password))
            .perform(typeText("aaaaAAAA"))

        onView(withId(R.id.login_button))
            .perform(click())

        onView(withText(R.string.invalid_password_error))
            .check(matches(isDisplayed()))
    }

    @Test
    fun givenEmailAndPasswordAreValid_whenLogin_shouldGoToHomeScreen() {
        Intents.intending(hasComponent(HomeActivity::class.java.name))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null))

        onView(withId(R.id.email))
            .perform(typeText("w.jonathan.marcolino@accenture.com"))
        onView(withId(R.id.password))
            .perform(typeText("aA#1dkjfjk"))

        onView(withId(R.id.login_button))
            .perform(click())

        intended(hasComponent(HomeActivity::class.java.name))
    }
}