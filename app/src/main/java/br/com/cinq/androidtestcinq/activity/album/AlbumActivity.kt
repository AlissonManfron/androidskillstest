package br.com.cinq.androidtestcinq.activity.album

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import br.com.cinq.androidtestcinq.R
import br.com.cinq.androidtestcinq.adapter.AlbumAdapter
import br.com.cinq.androidtestcinq.extensions.setupToolbar
import br.com.cinq.androidtestcinq.service.Album
import kotlinx.android.synthetic.main.activity_album.*
import org.jetbrains.anko.toast

class AlbumActivity : AppCompatActivity(), AlbumView {

    private var presenter: AlbumPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        setupToolbar(R.id.toolbar, "Álbuns", true)

        // Config recycler view
        setupRecyclerView()

        presenter = AlbumPresenterImpl(this, AlbumInteractorImpl())

        presenter?.loadAlbuns()

    }

    private fun setupRecyclerView() {
        rv_albuns.layoutManager = LinearLayoutManager(this)
        rv_albuns.itemAnimator = DefaultItemAnimator()
        rv_albuns.setHasFixedSize(true)
    }

    override fun setAlbuns(albuns: ArrayList<Album>) {
        // Atualiza a lista
        runOnUiThread {
            val adapter = AlbumAdapter(albuns)
            rv_albuns.adapter = adapter
        }

    }

    override fun setErrorAlbuns() {
        toast("Ocorreu um erro ao carregar a lista de álbuns, tente novamente!")
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }
}
