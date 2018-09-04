package br.com.cinq.androidtestcinq.activity.register

import br.com.cinq.androidtestcinq.persistence.AppDatabase
import br.com.cinq.androidtestcinq.persistence.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegisterInteractorImpl : RegisterInteractor {


    override fun cadastrar(name: String, email: String, password: String, listener: RegisterInteractor.OnRegisterFinishedListener) {

        // Get instance database
        val database = AppDatabase.getInstance()?.userDao()

        // Create user
        val user = User(null, name, email, password)

        // Save user in background thread
        Observable.fromCallable { database?.insert(user) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null) listener.onSuccess() else listener.onError()
                }, {
                    listener.onError()
                })
    }
}