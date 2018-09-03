package br.com.cinq.androidtestcinq.activity.register

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RegisterInteractorImpl : RegisterInteractor{


    override fun cadastrar(email: String, name: String, senha: String, listener: RegisterInteractor.OnRegisterFinishedListener) {

        Observable.interval(2000, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener.onSuccess()
                })
    }
}