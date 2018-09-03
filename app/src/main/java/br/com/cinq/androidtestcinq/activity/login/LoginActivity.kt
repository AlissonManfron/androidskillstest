package br.com.cinq.androidtestcinq.activity.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import br.com.cinq.androidtestcinq.R
import br.com.cinq.androidtestcinq.activity.home.HomeActivity
import br.com.cinq.androidtestcinq.activity.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity(), LoginView {

    private var presenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        title = "Login" //Todo: trocar depois

        // Action Login
        btn_login.setOnClickListener { onClickLogin() }

        // Action register
        btn_navigate_register.setOnClickListener { onClickRegister() }

        // Validate e-mail
        edit_email_login.addTextChangedListener(onTextWatcherEmail())

        // validate password
        edit_password_login.addTextChangedListener(onTextWatcherSenha())

        // Get instance LoginPresenterImpl
        presenter = LoginPresenterImpl(this, LoginInteractorImpl())

        // Disable login button
        disableButton()
    }

    override fun showProgress() {
        runOnUiThread { progress_login.visibility = View.VISIBLE }
    }

    override fun hideProgress() {
        runOnUiThread { progress_login.visibility = View.GONE }
    }

    override fun setUsernameError(msg: String) {
        text_input_email_login.error = msg
    }

    override fun removeUsernameError() {
        text_input_email_login.error = null
    }

    override fun setPasswordError(msg: String) {
        text_input_password_login.error = msg
    }

    override fun removePasswordError() {
        text_input_password_login.error = null
    }

    override fun setLoginError(msg: String) {
        toast(msg)
    }

    override fun navigateToHome() {
        startActivity<HomeActivity>()
        finish()
    }

    override fun onClickLogin() {
        presenter?.validateCredentials(edit_email_login.text.toString(), edit_password_login.text.toString())
    }

    override fun onClickRegister() {
        startActivity<RegisterActivity>()
    }

    override fun enableButton() {
        btn_login.alpha = 1f
        btn_login.isClickable = true
    }

    override fun disableButton() {
        btn_login.alpha = .5f
        btn_login.isClickable = false
    }

    private fun onTextWatcherEmail(): TextWatcher {
        return (object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.onValidateEditTextEmail(edit_email_login.text.toString())
            }
        })
    }

    private fun onTextWatcherSenha(): TextWatcher {
        return (object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.onValidateEditTextPassword(edit_password_login.text.toString())
            }
        })
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }
}
