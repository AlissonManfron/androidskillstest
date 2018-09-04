package br.com.cinq.androidtestcinq.activity.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cinq.androidtestcinq.R
import br.com.cinq.androidtestcinq.extensions.setupToolbar

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupToolbar(R.id.toolbar, "Usuários", true)
    }
}
