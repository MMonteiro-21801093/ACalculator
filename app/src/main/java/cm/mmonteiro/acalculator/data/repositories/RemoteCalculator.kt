package cm.mmonteiro.acalculator.data.repositories

import android.content.ContentValues
import android.util.Log
import cm.mmonteiro.acalculator.data.room.dao.OperationDao
import cm.mmonteiro.acalculator.helpers.Constants
import cm.mmonteiro.acalculator.interfaces.HistoryViewModelInterface
import cm.mmonteiro.acalculator.models.Operation
import cm.mmonteiro.acalculator.remote.services.OperationsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class RemoteCalculator(val storage: OperationDao, val retrofit: Retrofit) {
    val constants = Constants.getInstance()

    fun performOperationWeb(operation: Operation, historyViewModelInterface: HistoryViewModelInterface) {
        val service = retrofit.create(OperationsService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.operation(constants.USER_TOKEN, operation)
            storage.insert(operation)
            historyViewModelInterface.returnMessage(response.body()!!.message)
        }

        if(!constants.INTERNET_CONNECTIVITY){
            updateWebServer(historyViewModelInterface)
        }
    }

    private fun updateWebServer(historyViewModelInterface: HistoryViewModelInterface) {
        constants.INTERNET_CONNECTIVITY = true
        CoroutineScope(Dispatchers.IO).launch {
            val service = retrofit.create(OperationsService::class.java)
            val listStorage = storage.getAll()
            for(item in listStorage){
                val response = service.operation(constants.USER_TOKEN, item)
            }
            val response = service.getAll(constants.USER_TOKEN)
            historyViewModelInterface.getAllHistory(response)
        }

    }

    fun performOperationDB(operation: Operation, historyViewModelInterface: HistoryViewModelInterface) {
        constants.INTERNET_CONNECTIVITY = false
        CoroutineScope(Dispatchers.IO).launch {
            storage.insert(operation)
            historyViewModelInterface.returnMessage("Ok")
        }
    }

    fun getAllWeb(historyViewModelInterface: HistoryViewModelInterface) {
        val service = retrofit.create(OperationsService::class.java)
        Log.i(ContentValues.TAG, constants.USER_TOKEN)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getAll(constants.USER_TOKEN)
            historyViewModelInterface.getAllHistory(response)
        }
    }

    fun getAllDB(historyViewModelInterface: HistoryViewModelInterface) {
        constants.INTERNET_CONNECTIVITY = false
        CoroutineScope(Dispatchers.IO).launch {
            historyViewModelInterface.getAllHistory(storage.getAll() as MutableList<Operation>)
        }
    }

    fun deleteAllWeb(historyViewModelInterface: HistoryViewModelInterface) {
        val service = retrofit.create(OperationsService::class.java)
        Log.i(ContentValues.TAG, constants.USER_TOKEN)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.delete(constants.USER_TOKEN)
            storage.deleteAll()
            historyViewModelInterface.returnMessage(response.body()?.message!!)
            getAllWeb(historyViewModelInterface)

        }
    }

    fun deleteAllDB(historyViewModelInterface: HistoryViewModelInterface) {
        constants.INTERNET_CONNECTIVITY = false
        CoroutineScope(Dispatchers.IO).launch {
            storage.deleteAll()
          //  historyViewModelInterface.returnMessage("Items eliminados da BD")
            historyViewModelInterface.getAllHistory(storage.getAll() as MutableList<Operation>)
        }
    }

}
