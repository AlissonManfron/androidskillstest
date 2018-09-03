package br.com.cinq.androidtestcinq.activity.login

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class LoginInteractorImpl : LoginInteractor {

    override fun login(username: String, password: String, listener: LoginInteractor.OnLoginFinishedListener) {
        Observable.interval(2000, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener.onSuccess()
                })
    }

}