package br.com.cinq.androidtestcinq.activity.login

import br.com.cinq.androidtestcinq.MyApplication
import br.com.cinq.androidtestcinq.R
import br.com.cinq.androidtestcinq.extensions.isEmailValid

class LoginPresenter(private var loginView: LoginContract.LoginView?,
                     private val loginInteractor: LoginContract.LoginInteractor) : LoginContract.LoginPresenter {

    private var isEmailOk = false
    private var isPasswordOk = false

    override fun validateCredentials(username: String, password: String) {

        loginInteractor.login(username, password, object : LoginContract.LoginInteractor.OnLoginFinishedListener {
            override fun onSuccess() {
                loginView?.enableButton()
                loginView?.hideProgress()
                loginView?.navigateToHome()
            }

            override fun onError(msg: String) {
                loginView?.enableButton()
                loginView?.hideProgress()
                loginView?.setLoginError(msg)
            }
        })
    }

    override fun onValidateEditTextEmail(email: String) {
        when {
            email.isEmpty() -> {
                isEmailOk = false
                loginView?.disableButton()
                loginView?.setUsernameError(
                        MyApplication.getInstance().applicationContext.getString(R.string.email_is_empty))
            }
            !email.isEmailValid() -> {
                isEmailOk = false
                loginView?.disableButton()
                loginView?.setUsernameError(
                        MyApplication.getInstance().applicationContext.getString(R.string.email_error_invalid))
            }
            else -> {
                isEmailOk = true
                loginView?.removeUsernameError()
                if (isEmailOk && isPasswordOk)
                    loginView?.enableButton()
            }
        }
    }

    override fun onValidateEditTextPassword(password: String) {
        when {
            password.isEmpty() -> {
                isPasswordOk = false
                loginView?.disableButton()
                loginView?.setPasswordError(
                        MyApplication.getInstance().applicationContext.getString(R.string.password_error))
            }
            else -> {
                isPasswordOk = true
                loginView?.removePasswordError()
                if (isEmailOk && isPasswordOk)
                    loginView?.enableButton()
            }
        }
    }


    override fun onDestroy() {
        loginView = null
    }

}