package br.com.cinq.androidtestcinq.preference

import android.content.SharedPreferences
import br.com.cinq.androidtestcinq.MyApplication
import br.com.cinq.androidtestcinq.persistence.User

object Prefs {
    val PREF_ID = "user"
    private fun prefs(): SharedPreferences {
        val context = MyApplication.getInstance().applicationContext
        return context.getSharedPreferences(PREF_ID, 0)
    }

    fun setUser(user: User) {
        prefs().edit()
                .putLong("id", user.id!!)
                .putString("name", user.name)
                .putString("email", user.email)
                .putString("password", user.password)
                .apply()
    }

    fun getUser(): User {
        val id = prefs().getLong("id", 0)
        val name = prefs().getString("name", "")
        val email = prefs().getString("email",  "")
        val password = prefs().getString("password", "")
        return User(id, name, email, password)
    }
}