package cm.mmonteiro.acalculator.activities.register

import androidx.lifecycle.ViewModel
import cm.mmonteiro.acalculator.domain.AuthLogic
import cm.mmonteiro.acalculator.interfaces.LoginInterface
import cm.mmonteiro.acalculator.interfaces.LoginResponseInterface
import cm.mmonteiro.acalculator.remote.RetrofitBuilder
import cm.mmonteiro.acalculator.remote.responses.LoginResponse

const val ENDPOINT ="https://cm-calculadora.herokuapp.com/api/"
class RegisterViewModel:ViewModel() {
    private lateinit var listener: LoginResponseInterface
    private lateinit var loginInterface: LoginInterface
    private val authLogic = AuthLogic(RetrofitBuilder.getInstance(cm.mmonteiro.acalculator.activities.login.ENDPOINT))
    fun createNewUser(name: String, email: String, password: String) {
        authLogic.createUser(name,email,password,loginInterface)
    }
    fun registerListener(listener: LoginResponseInterface) {
        this.listener = listener
        loginInterface = object : LoginInterface {
            override fun resultLogin() {
                listener.responseOk()
            }

            override fun errorMsg(message: String) {
                listener.responseError(message)
            }


        }
    }
}