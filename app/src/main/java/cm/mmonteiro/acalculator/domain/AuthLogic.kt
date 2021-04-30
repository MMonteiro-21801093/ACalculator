package cm.mmonteiro.acalculator.domain

import cm.mmonteiro.acalculator.remote.requests.Login
import cm.mmonteiro.acalculator.remote.services.AuthService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class AuthLogic(private val retrofit: Retrofit) {

    fun authenticateUser(  email:String,password:String){
        val service = retrofit.create(AuthService::class.java)
        CoroutineScope(Dispatchers.IO).launch{
          val response = service.login(Login(email,password))
            if(response.isSuccessful){

            }
        }
    }
}