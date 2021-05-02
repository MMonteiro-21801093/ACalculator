package cm.mmonteiro.acalculator.remote.services

import android.media.VolumeShaper
import cm.mmonteiro.acalculator.models.Operation
import cm.mmonteiro.acalculator.remote.requests.Login
import cm.mmonteiro.acalculator.remote.requests.User
import cm.mmonteiro.acalculator.remote.responses.LoginResponse
import cm.mmonteiro.acalculator.remote.responses.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("users/login")
    suspend fun login(@Body body: Login):Response<LoginResponse>
    @POST("users/register")
    suspend fun register(@Body body: User):Response<UserResponse>

}