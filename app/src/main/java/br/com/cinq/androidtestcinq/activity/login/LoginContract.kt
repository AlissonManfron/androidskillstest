package br.com.cinq.androidtestcinq.activity.login

interface LoginView {

    fun showProgress()

    fun hideProgress()

    fun setUsernameError(msg: String)

    fun setPasswordError(msg: String)

    fun removeUsernameError()

    fun removePasswordError()

    fun setLoginError()

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

        fun onError()
    }

    fun login(email: String, password: String, listener: OnLoginFinishedListener)

}