package cm.mmonteiro.acalculator.remote.responses

import cm.mmonteiro.acalculator.helpers.Constants

class LoginResponse(val email:String,val token:String){
    var USER_TOKEN:String =email
    var USER_EMAIL:String =token
    companion object{
        private var instance: Constants? = null

        fun getInstance(): Constants {
            synchronized(this){
                if(instance == null){
                    instance = Constants()
                }
                return instance as Constants
            }
        }
    }

}
