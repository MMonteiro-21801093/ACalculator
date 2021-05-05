package cm.mmonteiro.acalculator.activities.register

import androidx.lifecycle.ViewModel
import cm.mmonteiro.acalculator.domain.AuthLogic
import cm.mmonteiro.acalculator.helpers.Constants
import cm.mmonteiro.acalculator.interfaces.LoginResponseInterface
import cm.mmonteiro.acalculator.interfaces.RegisterInterface
import cm.mmonteiro.acalculator.interfaces.RegisterResponseInterface
import cm.mmonteiro.acalculator.remote.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterViewModel:ViewModel() {
    private lateinit var listener: RegisterResponseInterface
    private lateinit var registerInterface: RegisterInterface
    val constants = Constants.getInstance()
    private val authLogic = AuthLogic(RetrofitBuilder.getInstance(constants.ENDPOINT))
    fun createNewUser(name: String, email: String, password: String) {
        authLogic.createUser(name,email,password,registerInterface)
    }
    fun registerListener(listener: RegisterResponseInterface) {
        this.listener = listener
        registerInterface = object : RegisterInterface {

            override fun createUserError(message: String) {
                CoroutineScope(Dispatchers.Main).launch {
                    listener.createUserError(message)
                }

            }
            override fun createUserSucess(message: String) {
                CoroutineScope(Dispatchers.Main).launch {
                    listener.createUserSucess(message)
                }

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