package br.com.cinq.androidtestcinq.activity.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import br.com.cinq.androidtestcinq.R
import br.com.cinq.androidtestcinq.activity.register.RegisterActivity
import br.com.cinq.androidtestcinq.adapter.UserAdapter
import br.com.cinq.androidtestcinq.event.DeleteUserEvent
import br.com.cinq.androidtestcinq.event.EditUserEvent
import br.com.cinq.androidtestcinq.extensions.setupToolbar
import br.com.cinq.androidtestcinq.persistence.User
import kotlinx.android.synthetic.main.activity_home.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.toast


class HomeActivity : AppCompatActivity(), HomeView {

    private var adapter: UserAdapter? = null
    private var presenter: HomePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // config toolbar
        setupToolbar(R.id.toolbar, "Usuários", true)

        // Config recycler view
        setupRecyclerView()

        // Fab button click
        fab_home.setOnClickListener { onClickAddUser() }

        // Get instance presenter
        presenter = HomePresenterImpl(this, HomeInteractorImpl())

    }

    private fun setupRecyclerView() {
        rv_users.layoutManager = LinearLayoutManager(this)
        rv_users.itemAnimator = DefaultItemAnimator()
        rv_users.setHasFixedSize(true)
    }

    override fun setNameUser(name: String) {
        txt_name_user.text = name
    }

    override fun setListUsers(users: MutableList<User>) {
        // Atualiza a lista
        runOnUiThread {
            adapter = UserAdapter(users)
            rv_users.adapter = adapter
        }
    }

    override fun setErrorLoadUsers() {
        toast("Ocorreu um erro ao listar os usuários!")
    }

    override fun removeUserFromList(user: User) {
        toast("Usuário removido com sucesso!")
        adapter?.onRemoveUser(user)
    }

    override fun showDialogDelete(user: User) {
        AlertDialog.Builder(this)
                .setTitle("Exclusão de Usuário")
                .setMessage("Opss!! Você não pode deletar o usuário : \n${user.email}, \npois ele está logado!")
                .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
    }

    override fun setErrorDeleteUser() {
        toast("Ocorreu um erro ao deletar o usuário, tente novamente!")
    }

    private fun onClickAddUser() {
        val it = Intent(this, RegisterActivity::class.java)
        it.putExtra("id", -1)
        startActivity(it)
    }

    override fun onResume() {
        super.onResume()
        presenter?.loadUser()
        presenter?.loadUsers()
    }

    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEditUserEvent(event: EditUserEvent) {
        val it = Intent(this, RegisterActivity::class.java)
        it.putExtra("id", event.user.id)
        startActivity(it)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onDeleteUserEvent(event: DeleteUserEvent) {
        Log.w("TAG Delete : ", event.user.email)
        presenter?.deleteUser(event.user)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)

        val mSearch = menu?.findItem(R.id.action_search)

        val mSearchView = mSearch?.actionView as SearchView
        mSearchView.queryHint = "Search"

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter?.filter?.filter(newText)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }
}
