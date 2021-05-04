package cm.mmonteiro.acalculator.domain

import cm.mmonteiro.acalculator.helpers.Constants
import cm.mmonteiro.acalculator.interfaces.LoginInterface
import cm.mmonteiro.acalculator.interfaces.RegisterInterface
import cm.mmonteiro.acalculator.remote.requests.Login
import cm.mmonteiro.acalculator.remote.requests.User
import cm.mmonteiro.acalculator.remote.services.AuthService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class AuthLogic(private val retrofit: Retrofit) {

    fun authenticateUser(email: String, password: String, loginInterface: LoginInterface){
        val service = retrofit.create(AuthService::class.java)
        CoroutineScope(Dispatchers.IO).launch{
          val response = service.login(Login(email,password))
            if(response.isSuccessful){
                val constants  = Constants.getInstance()
                constants.USER_TOKEN = response.body()!!.token
                constants.USER_EMAIL = response.body()!!.email
                loginInterface.resultLogin( )

            }else{
                loginInterface.errorMsg(response.message())
            }
        }
    }
    fun createUser(name: String, email: String, password: String, resgisterInterface: RegisterInterface){
        val service = retrofit.create(AuthService::class.java)
        CoroutineScope(Dispatchers.IO).launch{
            val response = service.register(User(name,email,password))
            if(response.isSuccessful){
                resgisterInterface.createUserSucess(response.body()!!.message)
            }else{
                resgisterInterface.createUserError(response.message())
            }
        }
    }


}