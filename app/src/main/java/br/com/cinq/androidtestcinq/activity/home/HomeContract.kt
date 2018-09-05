package br.com.cinq.androidtestcinq.activity.home

import br.com.cinq.androidtestcinq.persistence.User

interface HomeView {

    fun setNameUser(name: String)

    fun setListUsers(users: MutableList<User>)

    fun setErrorLoadUsers()

    fun removeUserFromList(user: User)

    fun showDialogDelete(user: User)

    fun setErrorDeleteUser()

}

interface HomeInteractor {

    interface OnUsersFinishedListener {

        fun onSuccess(users: MutableList<User>)

        fun onError()
    }
    fun getUsers(listener: OnUsersFinishedListener)


    interface OnDeleteUserFinishedListener {

        fun onSuccess(user: User)

        fun onError()
    }
    fun deleteUser(user: User, listener: OnDeleteUserFinishedListener)
}

interface HomePresenter {

    fun loadUsers()

    fun loadUser()

    fun deleteUser(user: User)

    fun onDestroy()

}