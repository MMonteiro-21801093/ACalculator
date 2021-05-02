package cm.mmonteiro.acalculator.domain

import android.content.ContentValues.TAG
import android.util.Log
import cm.mmonteiro.acalculator.interfaces.LoginInterface
import cm.mmonteiro.acalculator.models.Operation
import cm.mmonteiro.acalculator.remote.requests.Login
import cm.mmonteiro.acalculator.remote.requests.User
import cm.mmonteiro.acalculator.remote.responses.LoginResponse
import cm.mmonteiro.acalculator.remote.services.AuthService
import cm.mmonteiro.acalculator.remote.services.OperationsService
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
         //      var teste1 = response.raw()
                val login  = LoginResponse.getInstance()
                login.USER_TOKEN = response.body()!!.token
                login.USER_EMAIL = response.body()!!.email
                loginInterface.resultLogin( )

            }else{
                loginInterface.errorMsg(response.message())
            }
        }
    }
    fun createUser(name: String, email: String, password: String, loginInterface: LoginInterface){
        val service = retrofit.create(AuthService::class.java)
        CoroutineScope(Dispatchers.IO).launch{
            val response = service.register(User(name,email,password))
            if(response.isSuccessful){
                loginInterface.resultLogin( )
            }else{
                loginInterface.errorMsg(response.message())
            }
        }
    }


}