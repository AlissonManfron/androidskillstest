package br.com.cinq.androidtestcinq.activity.register

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import br.com.cinq.androidtestcinq.R
import br.com.cinq.androidtestcinq.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : AppCompatActivity(), RegisterView {

    private var presenter: RegisterPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Config Toolbar
        setupToolbar(R.id.toolbar, "Cadastro", true)

        // On click button save user
        btn_register.setOnClickListener({ onClickRegister() })

        // Validate name
        edit_name_register.addTextChangedListener(onTextWatcherName())

        // validate e-mail
        edit_email_register.addTextChangedListener(onTextWatcherEmail())

        // validate password
        edit_password_register.addTextChangedListener(onTextWatcherPassword())

        // Get instance presenter
        presenter = RegisterPresenterImpl(this, RegisterInteractorImpl())

        // Disable button register
        disableButton()
    }

    override fun showProgress() {
        runOnUiThread { progress_register.visibility = View.VISIBLE }
    }

    override fun hideProgress() {
        runOnUiThread {
            progress_register.visibility = View.GONE
        }
    }

    override fun setNameError(msg: String) {
        text_input_name_register.error = msg
    }

    override fun setEmailError(msg: String) {
        text_input_email_register.error = msg
    }

    override fun setPasswordError(msg: String) {
        text_input_password_register.error = msg
    }

    override fun removeNameError() {
        text_input_name_register.error = null
    }

    override fun removeEmailError() {
        text_input_email_register.error = null
    }

    override fun removePasswordError() {
        text_input_password_register.error = null
    }

    override fun setRegisterError() {
        toast("Não foi possível realizar seu cadastro, tente novamente!")
    }


    override fun setRegisterSuccess() {
        toast("Cadastro realizado com sucesso!")
        finish()
    }

    override fun enableButton() {
        btn_register.alpha = 1f
        btn_register.isClickable = true
    }

    override fun disableButton() {
        btn_register.alpha = 0.5f
        btn_register.isClickable = false
    }

    override fun onClickRegister() {
        presenter?.cadastrar(edit_name_register.text.toString(),
                edit_email_register.text.toString(), edit_password_register.text.toString())
    }

    private fun onTextWatcherPassword(): TextWatcher? {
        return (object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.onValidateEditTextPassword(edit_password_register.text.toString())
            }
        })
    }

    private fun onTextWatcherEmail(): TextWatcher? {
        return (object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.onValidateEditTextEmail(edit_email_register.text.toString())
            }
        })
    }

    private fun onTextWatcherName(): TextWatcher? {
        return (object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.onValidateEditTextName(edit_name_register.text.toString())
            }
        })
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }

}
