package br.com.cinq.androidtestcinq.service

import io.reactivex.Observable
import retrofit2.http.GET

interface AlbumREST {

    @GET("albums")
    fun getAlbuns(): Observable<ArrayList<Album>>
}