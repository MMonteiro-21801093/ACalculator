package cm.mmonteiro.acalculator.activities.register

import androidx.lifecycle.ViewModel
import cm.mmonteiro.acalculator.domain.AuthLogic
import cm.mmonteiro.acalculator.remote.RetrofitBuilder

const val ENDPOINT ="https://cm-calculadora.herokuapp.com/api/"
class RegisterViewModel:ViewModel() {
    private val authLogic = AuthLogic(RetrofitBuilder.getInstance(cm.mmonteiro.acalculator.activities.login.ENDPOINT))
    fun createNewUser(name: String, email: String, password: String) {
        authLogic.createUser(name,email,password)
    }

}