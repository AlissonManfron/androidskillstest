package br.com.cinq.androidtestcinq.activity.home

import br.com.cinq.androidtestcinq.persistence.AppDatabase
import br.com.cinq.androidtestcinq.persistence.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class HomeInteractorImpl : HomeInteractor {

    override fun getUsers(listener: HomeInteractor.OnUsersFinishedListener) {

        // Get instance database
        val database = AppDatabase.getInstance()?.userDao()

        // Save user in background thread
        database?.getAll()
                ?.subscribeOn(Schedulers.computation())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    if (it != null) listener.onSuccess(it) else listener.onError()
                }, {
                    listener.onError()
                })
    }

    override fun deleteUser(user: User, listener: HomeInteractor.OnDeleteUserFinishedListener) {

        // Get instance database
        val database = AppDatabase.getInstance()?.userDao()

        // Save user in background thread
        Observable.fromCallable { database?.deleteById(user.id!!) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null) listener.onSuccess(user) else listener.onError()
                }, {
                    if (it !is ConcurrentModificationException)
                        listener.onError()
                })
    }

}