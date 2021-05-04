package cm.mmonteiro.acalculator.activities.register

import androidx.lifecycle.ViewModel
import cm.mmonteiro.acalculator.domain.AuthLogic
import cm.mmonteiro.acalculator.interfaces.LoginResponseInterface
import cm.mmonteiro.acalculator.interfaces.RegisterInterface
import cm.mmonteiro.acalculator.interfaces.RegisterResponseInterface
import cm.mmonteiro.acalculator.remote.RetrofitBuilder

const val ENDPOINT ="https://cm-calculadora.herokuapp.com/api/"
class RegisterViewModel:ViewModel() {
    private lateinit var listener: RegisterResponseInterface
    private lateinit var registerInterface: RegisterInterface
    private val authLogic = AuthLogic(RetrofitBuilder.getInstance(cm.mmonteiro.acalculator.activities.login.ENDPOINT))
    fun createNewUser(name: String, email: String, password: String) {
        authLogic.createUser(name,email,password,registerInterface)
    }
    fun registerListener(listener: RegisterResponseInterface) {
        this.listener = listener
        registerInterface = object : RegisterInterface {

            override fun createUserError(message: String) {
                listener.createUserError(message)
            }
            override fun createUserSucess(message: String) {
                listener.createUserSucess(message)
                Thread.sleep(2000)
                listener.responseOk()
            }
            /*     override fun resultLogin() {
                     listener.responseOk()
                 }

                 override fun errorMsg(message: String) {
                     listener.responseError(message)
                 }*/




        }
    }
}