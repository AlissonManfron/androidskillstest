package br.com.cinq.androidtestcinq

import android.app.Application

class MyApplication : Application() {

    // Chamado quando o Android criar o processo da aplicação
    override fun onCreate() {
        super.onCreate()
        // Salva a instância para termos acesso como Singleton
        appInstance = this
    }

    companion object {
        // Singleton da classe Application
        private var appInstance: MyApplication? = null

        fun getInstance(): MyApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configure a classe de Application no AndroidManifest.xml")
            }
            return appInstance!!
        }
    }
}