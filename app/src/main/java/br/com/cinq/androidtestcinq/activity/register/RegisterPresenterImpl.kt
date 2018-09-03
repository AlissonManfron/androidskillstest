package br.com.cinq.androidtestcinq.activity.register

import br.com.cinq.androidtestcinq.MyApplication
import br.com.cinq.androidtestcinq.R
import br.com.cinq.androidtestcinq.extensions.isEmailValid

class RegisterPresenterImpl(private var registerView: RegisterView?,
                            private val registerInteractor: RegisterInteractor) : RegisterPresenter {

    private var isNameOk = false
    private var isEmailOk = false
    private var isPasswordOk = false

    override fun cadastrar(name: String, email: String, password: String) {
        registerView?.showProgress()
        registerView?.disableButton()

        registerInteractor.cadastrar(name, email, password, object : RegisterInteractor.OnRegisterFinishedListener {
            override fun onSuccess() {
                registerView?.enableButton()
                registerView?.hideProgress()
            }

            override fun onError() {
                registerView?.enableButton()
                registerView?.hideProgress()
            }
        })
    }

    override fun onValidateEditTextName(name: String) {
        when {
            name.isEmpty() -> {
                isNameOk = false
                registerView?.disableButton()
                registerView?.setNameError(
                        MyApplication.getInstance().applicationContext.getString(R.string.name_error))
            }
            else -> {
                isNameOk = true
                registerView?.removeNameError()
                if (isNameOk && isEmailOk && isPasswordOk)
                    registerView?.enableButton()
            }
        }
    }

    override fun onValidateEditTextEmail(email: String) {
        when {
            email.isEmpty() -> {
                isEmailOk = false
                registerView?.disableButton()
                registerView?.setEmailError(
                        MyApplication.getInstance().applicationContext.getString(R.string.email_is_empty))
            }
            !email.isEmailValid() -> {
                isEmailOk = false
                registerView?.disableButton()
                registerView?.setEmailError(
                        MyApplication.getInstance().applicationContext.getString(R.string.email_error_invalid))
            }
            else -> {
                isEmailOk = true
                registerView?.removeEmailError()
                if (isNameOk && isEmailOk && isPasswordOk)
                    registerView?.enableButton()
            }
        }
    }

    override fun onValidateEditTextPassword(password: String) {
        when {
            password.isEmpty() -> {
                isPasswordOk = false
                registerView?.disableButton()
                registerView?.setPasswordError(
                        MyApplication.getInstance().applicationContext.getString(R.string.password_error))
            }
            else -> {
                isPasswordOk = true
                registerView?.removePasswordError()
                if (isNameOk && isEmailOk && isPasswordOk)
                    registerView?.enableButton()
            }
        }
    }


    override fun onDestroy() {
        registerView = null
    }
}