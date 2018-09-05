package br.com.cinq.androidtestcinq.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import br.com.cinq.androidtestcinq.R
import br.com.cinq.androidtestcinq.event.DeleteUserEvent
import br.com.cinq.androidtestcinq.event.EditUserEvent
import br.com.cinq.androidtestcinq.persistence.User
import kotlinx.android.synthetic.main.item_users.view.*
import org.greenrobot.eventbus.EventBus

class UserAdapter(private var users: MutableList<User>) :
        RecyclerView.Adapter<UserAdapter.UserViewHolder>(), Filterable {
    override fun getFilter(): Filter {
        return itemFilter
    }

    private var itemFilter = ItemFilter()
    private var usersCopy: MutableList<User> = users

    override fun getItemCount() = this.users.size

    // Infla o layout do adapter e retorna o ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // Infla a view do adapter
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        // Retorna o ViewHolder que contém todas as views
        return UserViewHolder(view)
    }

    // Faz o bind para atualizar o valor das views com os dados do User
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        // Recupera o objeto user
        val user = users[position]
        val view = holder.itemView
        with(view) {
            // Atualiza os dados do user
            txt_name_user.text = user.name
            txt_email_user.text = user.email

            btn_edit_user.setOnClickListener { EventBus.getDefault().post(EditUserEvent(user)) }
            btn_delete_user.setOnClickListener { EventBus.getDefault().post(DeleteUserEvent(user)) }
        }
    }

    fun onRemoveUser(user: User) {
        for (u in users) {
            if (u.id == user.id) {
                users.remove(user)
                notifyDataSetChanged()
            }
        }
    }

    fun setUsers(filteredGalaxies: MutableList<User>) {
        this.users = filteredGalaxies
    }

    private inner class ItemFilter : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filterResults = Filter.FilterResults()


            if (constraint.isNotEmpty()) {
                //CHANGE TO UPPER
                val string = constraint.toString().toUpperCase()

                //HOLD FILTERS WE FIND
                val foundFilters = ArrayList<User>()

                //ITERATE CURRENT LIST
                for (user in users) {
                    //SEARCH
                    if (user.name.toUpperCase().contains(string)) {
                        //ADD IF FOUND
                        foundFilters.add(user)
                    }
                }

                //SET RESULTS TO FILTER LIST
                filterResults.count = foundFilters.size
                filterResults.values = foundFilters
            } else {
                //NO ITEM FOUND.LIST REMAINS INTACT
                filterResults.count = filterResults.count
                filterResults.values = usersCopy
            }

            //RETURN RESULTS
            return filterResults
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            setUsers(results.values as MutableList<User>)
            notifyDataSetChanged()
        }
    }

    // ViewHolder fica vazio pois usamos o import do Android Kotlin Extensions
    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view)
}