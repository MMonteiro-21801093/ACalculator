package cm.mmonteiro.acalculator.domain

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import cm.mmonteiro.acalculator.data.repositories.OperationRepository
import cm.mmonteiro.acalculator.helpers.Constants
import cm.mmonteiro.acalculator.interfaces.HistoryViewModelInterface
import cm.mmonteiro.acalculator.models.Operation
import cm.mmonteiro.acalculator.remote.services.OperationsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorLogic(private val operationRepository: OperationRepository) {

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

    fun performOperation(
        expression: String,
        historyViewModelInterface: HistoryViewModelInterface,
        context: Context
    ): Double {
        val result = ExpressionBuilder(expression).build()
        operationRepository.performOperation(historyViewModelInterface,Operation(expression, result.evaluate()),context)
        return result.evaluate()
    }

    fun historyGetAll(historyViewModelInterface: HistoryViewModelInterface, context: Context) {
        operationRepository.getAll(historyViewModelInterface,context)
    }

    suspend fun delete(id: String, historyViewModelInterface: HistoryViewModelInterface) {
        /*     withContext(Dispatchers.IO){
               storage.delete(id)
                 historyViewModelInterface.getAllHistory(storage.getAll())
           }*/
    }

    fun deleteAll(historyViewModelInterface: HistoryViewModelInterface, context: Context) {
        operationRepository.deleteAll(historyViewModelInterface,context)

    }
}