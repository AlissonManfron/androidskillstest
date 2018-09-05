package br.com.cinq.androidtestcinq.activity.album

import br.com.cinq.androidtestcinq.service.Album

class AlbumPresenterImpl(private var albumView: AlbumView?,
                         private var albumInteractor: AlbumInteractor) : AlbumPresenter {


    override fun loadAlbuns() {

        albumInteractor.loadAlbuns(object : AlbumInteractor.OnAlbunsFinishedListener {
            override fun onSuccess(albuns: ArrayList<Album>) {
                albumView?.setAlbuns(albuns)
            }

            override fun onError() {
                albumView?.setErrorAlbuns()
            }
        })
    }

    override fun onDestroy() {
        albumView = null
    }
}