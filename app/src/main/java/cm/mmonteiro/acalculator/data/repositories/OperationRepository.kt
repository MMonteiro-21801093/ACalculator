package cm.mmonteiro.acalculator.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import cm.mmonteiro.acalculator.helpers.Constants
import cm.mmonteiro.acalculator.interfaces.HistoryViewModelInterface
import cm.mmonteiro.acalculator.models.Operation
import cm.mmonteiro.acalculator.remote.services.OperationsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OperationRepository(val remoteCalculator: RemoteCalculator) {


    fun performOperation(
        historyViewModelInterface: HistoryViewModelInterface,
        operation: Operation
    ) {
        if (isNetworkAvailbale()) {
            remoteCalculator.performOperationWeb(operation, historyViewModelInterface)
        } else {
            remoteCalculator.performOperationDB(operation, historyViewModelInterface)
        }
    }

    fun getAll(historyViewModelInterface: HistoryViewModelInterface) {
        if (isNetworkAvailbale()) {
            remoteCalculator.getAllWeb(historyViewModelInterface)
        } else {
            remoteCalculator.getAllDB(historyViewModelInterface)
        }
    }

    fun isNetworkAvailbale(): Boolean {

        val conManager =
            remoteCalculator.application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInfo = conManager.activeNetworkInfo
        return internetInfo != null && internetInfo.isConnected
    }

    fun deleteAll(historyViewModelInterface: HistoryViewModelInterface) {
        if(isNetworkAvailbale()){
            remoteCalculator.deleteAllWeb(historyViewModelInterface)
        }else{
            remoteCalculator.deleteAllDB(historyViewModelInterface)
        }
    }
}