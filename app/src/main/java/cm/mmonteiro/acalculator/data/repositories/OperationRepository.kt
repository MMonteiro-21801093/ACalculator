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
        operation: Operation,
        context: Context
    ) {
        if (isNetworkAvailbale(context)) {
            remoteCalculator.performOperationWeb(operation, historyViewModelInterface)
        } else {
            remoteCalculator.performOperationDB(operation, historyViewModelInterface)
        }
    }

    fun getAll(historyViewModelInterface: HistoryViewModelInterface, context: Context) {
        if (isNetworkAvailbale(context)) {
            remoteCalculator.getAllWeb(historyViewModelInterface)
        } else {
            remoteCalculator.getAllDB(historyViewModelInterface)
        }
    }

    fun isNetworkAvailbale(context: Context): Boolean {
        val conManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInfo = conManager.activeNetworkInfo
        return internetInfo != null && internetInfo.isConnected
    }

    fun deleteAll(historyViewModelInterface: HistoryViewModelInterface, context: Context) {
        if(isNetworkAvailbale(context)){
            remoteCalculator.deleteAllWeb(historyViewModelInterface)
        }else{
            remoteCalculator.deleteAllDB(historyViewModelInterface)
        }
    }
}