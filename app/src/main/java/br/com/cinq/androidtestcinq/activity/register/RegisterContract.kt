package br.com.cinq.androidtestcinq.activity.register

import br.com.cinq.androidtestcinq.persistence.User

interface RegisterView {

    fun showProgress()

    fun hideProgress()

    fun setEmailError(msg: String)

    fun setNameError(msg: String)

    fun setPasswordError(msg: String)

    fun removeNameError()

    fun removeEmailError()

    fun removePasswordError()

    fun setRegisterSuccess(msg: String)

    fun setRegisterError()

    fun enableButton()

    fun disableButton()

    fun onClickRegister()

    fun setUser(user: User)

}

interface RegisterPresenter {

    fun cadastrar(name: String, email: String, password: String)

    fun onValidateEditTextName(name: String)

    fun onValidateEditTextEmail(email: String)

    fun onValidateEditTextPassword(password: String)

    fun verifyEditMode(id: Long)

    fun onDestroy()

}

interface RegisterInteractor {

    interface OnRegisterFinishedListener {

        fun onSuccess(msg: String)

        fun onError()
    }

    fun cadastrar(name: String, email: String, password: String, listener: OnRegisterFinishedListener)


    interface OnFindUserFinishedListener {

        fun onSuccess(user: User)

        fun onError()
    }

    fun getUserById(id: Long, listener: OnFindUserFinishedListener)
}