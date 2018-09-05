package br.com.cinq.androidtestcinq.activity.album

import br.com.cinq.androidtestcinq.service.Album

interface AlbumView {

    fun setAlbuns(albuns: ArrayList<Album>)

    fun setErrorAlbuns()
}

interface AlbumInteractor {

    interface OnAlbunsFinishedListener {

        fun onSuccess(albuns: ArrayList<Album>)

        fun onError()
    }
    fun loadAlbuns(listener: OnAlbunsFinishedListener)
}

interface AlbumPresenter {

    fun loadAlbuns()

    fun onDestroy()
}