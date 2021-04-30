package cm.mmonteiro.acalculator.remote.requests

import com.google.gson.annotations.SerializedName

data class User (val name :String,val email: String, val password: String)
