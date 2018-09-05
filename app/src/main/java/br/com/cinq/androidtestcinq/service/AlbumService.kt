package br.com.cinq.androidtestcinq.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object AlbumService {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private var service: AlbumREST

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(AlbumREST::class.java)
    }

    // Busca uma lista de albuns
    fun getCarros() = service.getAlbuns()
}