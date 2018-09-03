package br.com.cinq.androidtestcinq.activity.register

interface RegisterView {

    fun showProgress()

    fun hideProgress()

    fun setEmailError(msg: String)

    fun setNameError(msg: String)

    fun setPasswordError(msg: String)

    fun removeNameError()

    fun removeEmailError()

    fun removePasswordError()

    fun setRegisterSuccess()

    fun setRegisterError()

    fun enableButton()

    fun disableButton()

    fun onClickRegister()

}

interface RegisterPresenter {

    fun cadastrar(name: String, email: String, password: String)

    fun onValidateEditTextName(name: String)

    fun onValidateEditTextEmail(email: String)

    fun onValidateEditTextPassword(password: String)

    fun onDestroy()
}

interface RegisterInteractor {

    interface OnRegisterFinishedListener {

        fun onSuccess()

        fun onError()
    }

    fun cadastrar(name: String, email: String, password: String, listener: OnRegisterFinishedListener)
}