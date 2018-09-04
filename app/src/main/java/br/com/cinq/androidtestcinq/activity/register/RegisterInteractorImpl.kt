package br.com.cinq.androidtestcinq.activity.register

import android.arch.persistence.room.EmptyResultSetException
import br.com.cinq.androidtestcinq.persistence.AppDatabase
import br.com.cinq.androidtestcinq.persistence.User
import br.com.cinq.androidtestcinq.preference.Prefs
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegisterInteractorImpl : RegisterInteractor {

    override fun cadastrar(name: String, email: String, password: String, listener: RegisterInteractor.OnRegisterFinishedListener) {

        // Get instance database
        val database = AppDatabase.getInstance()?.userDao()

        // Create user
        val user = User(null, name, email, password)

        // Checks whether the user exists
        database?.getByEmail(email)
                ?.subscribeOn(Schedulers.computation())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    // Update user
                    user.id = it.id
                    Observable.fromCallable { database.update(user) }
                            ?.subscribeOn(Schedulers.computation())
                            ?.observeOn(AndroidSchedulers.mainThread())
                            ?.subscribe({
                                Prefs.setUser(user)
                                listener.onSuccess("Usuário atualizado com sucesso!")
                            }, {
                                listener.onError()
                            })

                }, {
                    if (it is EmptyResultSetException) {
                        // Save new user
                        Observable.fromCallable { database.insert(user) }
                                ?.subscribeOn(Schedulers.computation())
                                ?.observeOn(AndroidSchedulers.mainThread())
                                ?.subscribe({
                                    listener.onSuccess("Cadastro realizado com sucesso!")
                                }, {
                                    listener.onError()
                                })
                    } else {
                        listener.onError()
                    }
                })
    }

    override fun getUserById(id: Long, listener: RegisterInteractor.OnFindUserFinishedListener) {

        // Get instance database
        val database = AppDatabase.getInstance()?.userDao()

        // Save user in background thread
        database?.getById(id)
                ?.subscribeOn(Schedulers.computation())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    if (it != null) listener.onSuccess(it) else listener.onError()
                }, {
                    listener.onError()
                })
    }
}