package cm.mmonteiro.acalculator.helpers

import cm.mmonteiro.acalculator.data.list.ListStorage

class Constants {
    var USER_TOKEN:String =""
    var USER_EMAIL:String =""
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
    fun saveAuthToken(token: String) {
        USER_TOKEN = token
    }
    fun saveAuthEmail(email: String) {
        USER_EMAIL = email
    }
}