package br.com.cinq.androidtestcinq.activity.register

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.cinq.androidtestcinq.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        title = "Cadastro" // Todo: alterar

    }
}
