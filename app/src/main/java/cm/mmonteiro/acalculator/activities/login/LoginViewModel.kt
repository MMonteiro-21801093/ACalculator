package cm.mmonteiro.acalculator.activities.login

import androidx.lifecycle.ViewModel
import cm.mmonteiro.acalculator.domain.AuthLogic
import cm.mmonteiro.acalculator.remote.RetrofitBuilder

const val ENDPOINT ="https://cm-calculadora.herokuapp.com/api/"
class LoginViewModel: ViewModel() {

    private val authLogic = AuthLogic(RetrofitBuilder.getInstance(ENDPOINT))
    fun logIn(email: String, password: String) {
        authLogic.authenticateUser(email,password)
    }

}