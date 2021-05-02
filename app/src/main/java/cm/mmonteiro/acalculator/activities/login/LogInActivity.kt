package cm.mmonteiro.acalculator.activities.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import cm.mmonteiro.acalculator.R
import cm.mmonteiro.acalculator.activities.MainActivity
import cm.mmonteiro.acalculator.activities.register.RegisterActivity
import cm.mmonteiro.acalculator.interfaces.LoginInterface
import cm.mmonteiro.acalculator.interfaces.LoginResponseInterface
import cm.mmonteiro.acalculator.remote.responses.LoginResponse
import kotlinx.android.synthetic.main.activity_log_in.*


class LogInActivity : AppCompatActivity(), LoginResponseInterface {
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        ButterKnife.bind(this)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        viewModel.registerListener(this)
    }

    @OnClick(R.id.button_login,R.id.text_new_user)
    fun onclickObject(view: View) {
        val symbol = view.tag.toString()
        if(symbol=="logIn"){
            viewModel.logIn("cm@ulusofona.pt","cm")
        }
        if(symbol=="register"){
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun responseOk( ) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun responseError(msg: String) {
        Toast.makeText(
            this,
            msg,
            Toast.LENGTH_SHORT
        ).show()
    }
}