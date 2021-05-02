package cm.mmonteiro.acalculator.interfaces

import cm.mmonteiro.acalculator.remote.responses.LoginResponse

interface LoginResponseInterface  {
    fun responseOk( )
    fun responseError( msg:String)
}