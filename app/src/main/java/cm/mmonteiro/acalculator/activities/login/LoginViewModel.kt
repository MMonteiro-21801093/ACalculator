package cm.mmonteiro.acalculator.activities.login

import androidx.lifecycle.ViewModel
import cm.mmonteiro.acalculator.data.list.ListStorage
import cm.mmonteiro.acalculator.domain.AuthLogic
import cm.mmonteiro.acalculator.interfaces.LoginInterface
import cm.mmonteiro.acalculator.interfaces.LoginResponseInterface
import cm.mmonteiro.acalculator.remote.RetrofitBuilder
import cm.mmonteiro.acalculator.remote.responses.LoginResponse

const val ENDPOINT ="https://cm-calculadora.herokuapp.com/api/"
class LoginViewModel: ViewModel() {
    private lateinit var listener: LoginResponseInterface
    private lateinit var loginInterface: LoginInterface
    private val authLogic = AuthLogic(RetrofitBuilder.getInstance(ENDPOINT))
    fun logIn(email: String, password: String) {

        authLogic.authenticateUser(email,password,loginInterface)
        
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


