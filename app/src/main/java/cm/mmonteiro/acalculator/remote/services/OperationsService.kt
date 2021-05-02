package cm.mmonteiro.acalculator.remote.services

import cm.mmonteiro.acalculator.models.Operation
import cm.mmonteiro.acalculator.remote.responses.OperationResponse
import retrofit2.Response
import retrofit2.http.*

interface OperationsService {

    @POST("operations")
    suspend fun operation(@Header("Authorization") token:String ,@Body body: Operation): Response<OperationResponse>

    @GET("operations")
    suspend fun getAll(@Header("Authorization") token:String ):MutableList<Operation>

    @DELETE("operations")
    suspend fun delete(@Header("Authorization") token:String ): Response<OperationResponse>
}