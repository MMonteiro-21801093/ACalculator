package cm.mmonteiro.acalculator.views.calculator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import cm.mmonteiro.acalculator.data.room.CalculatorDatabase
import cm.mmonteiro.acalculator.interfaces.HistoryViewModelInterface
import cm.mmonteiro.acalculator.interfaces.OnDisplayChanged
import cm.mmonteiro.acalculator.models.Operation
import cm.mmonteiro.acalculator.remote.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CalculatorViewModel(application: Application) : AndroidViewModel(application) {

    private val storage = CalculatorDatabase.getInstance(application).operationDao()

    //  private val calculatorLogic = CalculatorLogic(storage)
    private val calculatorLogic =
        CalculatorLogic(RetrofitBuilder.getInstance(cm.mmonteiro.acalculator.activities.login.ENDPOINT))
    var display: String = ""
    private var lastOperaton: String = ""
    private var listener: OnDisplayChanged? = null

    //private val storage = ListStorage.getInstance()
    private lateinit var historyViewModelInterface: HistoryViewModelInterface


    fun onclickSimbol(symbol: String) {
        display = calculatorLogic.insertSymbol(display, symbol)
        notifyOnDisplayChanged()
    }

    fun onClickEquals() {
        lastOperaton = display
        val result = calculatorLogic.performOperation(display,historyViewModelInterface)
        display = result.toString()
        notifyOnDisplayChanged()
    }

    fun getLastOperation(): String = "${lastOperaton} = ${display}"

    fun unregisterListener() {
        listener = null
    }

    fun registerListener(listener: OnDisplayChanged) {
        this.listener = listener
        listener?.onDisplayChanged(display)
        historyViewModelInterface = object : HistoryViewModelInterface {
            override fun getAllHistory(values: MutableList<Operation>) {
                CoroutineScope(Dispatchers.Main).launch {
                    listener.setHistoryList(values)
                }
            }

            override fun returnMessage(message: String) {
                CoroutineScope(Dispatchers.Main).launch {
                    listener?.onToastChanged(message)

                    //historyViewModelInterface.getAllHistory(storage)
                }

            }

        }
    }

    private fun notifyOnDisplayChanged() {
        listener?.onDisplayChanged(display)
    }

    fun updateAdapter() {
        Thread.sleep(3000)
          historyGetAll()
    }

    fun onItemClick(result: String) {
        listener?.onToastChanged(result)
    }

    fun longClickdeleteItem(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            calculatorLogic.delete(id, historyViewModelInterface)
        }


    }

    fun historyGetAll() {
        calculatorLogic.historyGetAll(historyViewModelInterface)
        /*CoroutineScope(Dispatchers.IO).launch{
            calculatorLogic.historyGetAll(historyViewModelInterface)
        }*/

    }

}
