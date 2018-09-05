package br.com.cinq.androidtestcinq.activity.home

import android.media.midi.MidiManager
import br.com.cinq.androidtestcinq.persistence.User
import br.com.cinq.androidtestcinq.preference.Prefs

class HomePresenterImpl(private var homeView: HomeView?,
                        private val homeInteractor: HomeInteractor) : HomePresenter {

    override fun loadUser() {
        val user = Prefs.getUser()
        homeView?.setNameUser(user.name)
    }

    override fun loadUsers() {

        homeInteractor.getUsers(object : HomeInteractor.OnUsersFinishedListener {
            override fun onSuccess(users: MutableList<User>) {
                homeView?.setListUsers(users)
            }

            override fun onError() {
                homeView?.setErrorLoadUsers()
            }
        })
    }

    override fun deleteUser(user: User) {

        // Verifica se o usuário a ser deletado não é o mesmo que está logado
        val currentUser = Prefs.getUser()
        if (currentUser.email == user.email) {
            homeView?.showDialogDelete(user)
            return
        }

        homeInteractor.deleteUser(user, object : HomeInteractor.OnDeleteUserFinishedListener {
            override fun onSuccess(user: User) {
                homeView?.removeUserFromList(user)
            }

            override fun onError() {
                homeView?.setErrorDeleteUser()
            }
        })
    }

    override fun onDestroy() {
        homeView = null
    }
}