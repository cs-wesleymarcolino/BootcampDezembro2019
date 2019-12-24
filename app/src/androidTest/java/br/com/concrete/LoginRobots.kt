package br.com.concrete

import android.app.Activity
import android.app.Instrumentation
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

class loginArrange(action: loginArrange.() -> Unit) {
    init {
        action.invoke(this)
    }

    fun mockHomeActivityIntent() {
        intending(hasComponent(HomeActivity::class.java.name))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_CANCELED, null))
    }
}

class loginAct(action: loginAct.() -> Unit) {
    init {
        action.invoke(this)
    }

    fun typeValidEmail() {
        onView(withId(R.id.email))
            .perform(typeText("w.jonathan.marcolino@accenture.com"))
    }

    fun typePassword(password: String) {
        onView(withId(R.id.password))
            .perform(typeText(password))
    }

    fun clickLogin() {
        onView(withId(R.id.login_button))
            .perform(ViewActions.click())
    }
}

class loginAssert(action: loginAssert.() -> Unit) {
    init {
        action.invoke(this)
    }

    fun checkHomeActivityWasCalled() {
        intended(hasComponent(HomeActivity::class.java.name))
    }

    fun checkEmailIsEmpty() {
        onView(withId(R.id.email))
            .check(matches(withText("")))
    }

    fun checkPasswordIsEmpty() {
        onView(withId(R.id.password))
            .check(matches(withText("")))
    }

    fun checkMessageIsShown(@StringRes errorRes: Int) {
        onView(withText(errorRes))
            .check(matches(ViewMatchers.isDisplayed()))
    }
}
