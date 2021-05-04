package cm.mmonteiro.acalculator.interfaces

interface RegisterResponseInterface {
    fun createUserError( msg:String)
    fun createUserSucess(message: String)
    fun responseOk( )
}