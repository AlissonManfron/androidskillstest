package br.com.cinq.androidtestcinq.activity.menu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cinq.androidtestcinq.R
import br.com.cinq.androidtestcinq.activity.home.HomeActivity
import br.com.cinq.androidtestcinq.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.startActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        setupToolbar(R.id.toolbar, "Cinq Test")

        btn_home.setOnClickListener { onClickHome() }

        btn_albuns.setOnClickListener { onClickAlbuns() }
    }

    private fun onClickHome() {
        startActivity<HomeActivity>()
    }

    private fun onClickAlbuns() {
        startActivity<HomeActivity>()
    }
}
