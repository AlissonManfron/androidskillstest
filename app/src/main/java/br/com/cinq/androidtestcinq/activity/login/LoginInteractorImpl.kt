package br.com.cinq.androidtestcinq.activity.login

import br.com.cinq.androidtestcinq.persistence.AppDatabase
import br.com.cinq.androidtestcinq.preference.Prefs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginInteractorImpl : LoginInteractor {

    override fun login(email: String, password: String, listener: LoginInteractor.OnLoginFinishedListener) {

        // Get instance database
        val database = AppDatabase.getInstance()?.userDao()

        // Validate user in background thread
        database?.validate(email, password)
                ?.subscribeOn(Schedulers.computation())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    if (it != null) {
                        Prefs.setUser(it)
                        listener.onSuccess()
                    } else {
                        listener.onError()
                    }
                }, {
                    listener.onError()
                })
    }

}