package br.com.cinq.androidtestcinq.activity.login

interface LoginContract {

    interface LoginView {

        fun showProgress()

        fun hideProgress()

        fun setUsernameError(msg: String)

        fun setPasswordError(msg: String)

        fun removeUsernameError()

        fun removePasswordError()

        fun setLoginError(msg: String)

        fun navigateToHome()

        fun onClickLogin()

        fun onClickRegister()

        fun enableButton()

        fun disableButton()
    }

    interface LoginPresenter {

        fun validateCredentials(username: String, password: String)

        fun onValidateEditTextEmail(email: String)

        fun onValidateEditTextPassword(password: String)

        fun onDestroy()
    }

    interface LoginInteractor {

        interface OnLoginFinishedListener {

            fun onSuccess()

            fun onError(msg: String)
        }

        fun login(username: String, password: String, callback: OnLoginFinishedListener)

    }

}