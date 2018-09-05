package br.com.cinq.androidtestcinq.activity.album

import br.com.cinq.androidtestcinq.service.AlbumService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AlbumInteractorImpl : AlbumInteractor {

    override fun loadAlbuns(listener: AlbumInteractor.OnAlbunsFinishedListener) {

        AlbumService.getCarros()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener.onSuccess(it)
                }, {
                    listener.onError()
                })
    }
}