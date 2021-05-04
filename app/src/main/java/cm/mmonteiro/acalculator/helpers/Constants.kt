package cm.mmonteiro.acalculator.helpers

import cm.mmonteiro.acalculator.data.list.ListStorage

class Constants {
    var USER_TOKEN:String =""
    var USER_EMAIL:String =""
    val ENDPOINT ="https://cm-calculadora.herokuapp.com/api/"
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