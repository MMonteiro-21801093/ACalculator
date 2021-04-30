package cm.mmonteiro.acalculator.activities.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import cm.mmonteiro.acalculator.R
import cm.mmonteiro.acalculator.views.calculator.CalculatorViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        ButterKnife.bind(this)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)

    }
    override fun onStart() {
        super.onStart()

    }
    @OnClick(R.id.button_submit,R.id.button_cancel)
    fun onclickObject(view: View) {
        val symbol = view.tag.toString()
        if(symbol=="cancel"){
            finish()
        }
        if(symbol=="submit"){
            viewModel.createNewUser(editName.text.toString(),editEmail.text.toString(),editPassword.text.toString())
        }
    }
}