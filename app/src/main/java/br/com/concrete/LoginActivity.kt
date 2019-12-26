package br.com.concrete

import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val loginViewModel by lazy {
        ViewModelProviders.of(
            this,
            LoginViewModelFactory(LoginUseCase(PasswordValidator()))
        ).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel.getViewState().observe(this, Observer {
            when (it) {
                is LoginViewState.LoginSuccessful -> navigateHome()
                is LoginViewState.LoginError -> showError(it.message)
            }
        })

        login_button.setOnClickListener {
            when {
                email.text.isEmpty() -> showError(R.string.email_is_empty_error)
                else -> {
                    loginViewModel.checkCredentials(password.text.toString())
                }
            }
        }
    }

    private fun navigateHome() {
        val homeActivityIntent = Intent(this, HomeActivity::class.java)
        startActivity(homeActivityIntent)
    }

    private fun showError(@StringRes errorRes: Int) {
        AlertDialog.Builder(this)
            .setMessage(errorRes)
            .show()
    }
}
