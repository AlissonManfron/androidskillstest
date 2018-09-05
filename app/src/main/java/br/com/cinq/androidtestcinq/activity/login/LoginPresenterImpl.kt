package br.com.cinq.androidtestcinq.activity.login

import br.com.cinq.androidtestcinq.MyApplication
import br.com.cinq.androidtestcinq.R
import br.com.cinq.androidtestcinq.extensions.isEmailValid

class LoginPresenterImpl(private var loginView: LoginView?,
                         private val loginInteractor: LoginInteractor?) : LoginPresenter {

    private var isEmailOk = false
    private var isPasswordOk = false

    override fun validateCredentials(username: String, password: String) {
        loginView?.showProgress()
        loginView?.disableButton()

        loginInteractor?.login(username, password, object : LoginInteractor.OnLoginFinishedListener {
            override fun onSuccess() {
                loginView?.enableButton()
                loginView?.hideProgress()
                loginView?.navigateToMenu()
            }

            override fun onError() {
                loginView?.enableButton()
                loginView?.hideProgress()
                loginView?.setLoginError()
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