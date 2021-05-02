package cm.mmonteiro.acalculator.domain

import android.content.ContentValues.TAG
import android.util.Log
import cm.mmonteiro.acalculator.interfaces.HistoryViewModelInterface
import cm.mmonteiro.acalculator.models.Operation
import cm.mmonteiro.acalculator.remote.responses.LoginResponse
import cm.mmonteiro.acalculator.remote.services.OperationsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import retrofit2.Retrofit

const val ENDPOINT = "https://cm-calculadora.herokuapp.com/api/"

class CalculatorLogic(private val retrofit: Retrofit) {

    // private val storage = ListStorage.getInstance()
    fun insertSymbol(display: String, symbol: String): String {

        when (symbol) {
            "CE" -> return ""
            "<" -> return display.substring(0, display.length - 1)
            in "0".."9" -> {
                if (display == "0") {
                    return symbol
                } else {
                    return display.plus(symbol)
                }
            }
            else -> return display.plus(symbol)
        }
    }

    fun performOperation(expression: String, historyViewModelInterface: HistoryViewModelInterface): Double {
        val result = ExpressionBuilder(expression).build()
        val operation = Operation(expression, result.evaluate())
        val service = retrofit.create(OperationsService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val loginResponse = LoginResponse.getInstance()
            val response = service.operation(loginResponse.USER_TOKEN, operation)
            historyViewModelInterface.returnMessage(response.body()!!.message)
        }
        /*    CoroutineScope(Dispatchers.IO).launch{
               // storage.insert(operation)

            }*/
        return result.evaluate()
    }

    /*    suspend fun historyGetAll(historyViewModelInterface: HistoryViewModelInterface) {
          withContext(Dispatchers.IO){
               historyViewModelInterface.getAllHistory(storage.getAll())
           }

       }*/
    fun historyGetAll(historyViewModelInterface: HistoryViewModelInterface) {
        val service = retrofit.create(OperationsService::class.java)
        val loginResponse = LoginResponse.getInstance()
        Log.i(TAG, loginResponse.USER_TOKEN)
        CoroutineScope(Dispatchers.IO).launch {

            val response = service.getAll(loginResponse.USER_TOKEN)

            historyViewModelInterface.getAllHistory(response)
            /*           if(response.){
                           val operations = response.body()
                           historyViewModelInterface.getAllHistory(operations)
                       }else{

                       }*/
        }

    }

    suspend fun delete(id: String, historyViewModelInterface: HistoryViewModelInterface) {
        /*     withContext(Dispatchers.IO){
               storage.delete(id)
                 historyViewModelInterface.getAllHistory(storage.getAll())
           }*/
    }

    fun deleteAll(historyViewModelInterface: HistoryViewModelInterface) {
        val service = retrofit.create(OperationsService::class.java)
        val loginResponse = LoginResponse.getInstance()
        Log.i(TAG, loginResponse.USER_TOKEN)
        CoroutineScope(Dispatchers.IO).launch {

            val response = service.delete(loginResponse.USER_TOKEN)
            historyViewModelInterface.returnMessage(response.body()?.message!!)
            historyGetAll(historyViewModelInterface)
    /*        if (response.isSuccessful) {
                historyGetAll(historyViewModelInterface)
            } else {
                historyViewModelInterface.returnMessage(response.body()?.message!!)
            }
*/
            /*           if(response.){
                           val operations = response.body()
                           historyViewModelInterface.getAllHistory(operations)
                       }else{

                       }*/
        }
    }
}