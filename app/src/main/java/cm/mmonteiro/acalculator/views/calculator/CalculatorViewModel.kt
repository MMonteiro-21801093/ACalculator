package cm.mmonteiro.acalculator.views.calculator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import cm.mmonteiro.acalculator.data.list.ListStorage
import cm.mmonteiro.acalculator.data.room.CalculatorDatabase
import cm.mmonteiro.acalculator.interfaces.HistoryViewModelInterface
import cm.mmonteiro.acalculator.interfaces.OnDisplayChanged
import cm.mmonteiro.acalculator.models.Operation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel(application: Application) : AndroidViewModel(application) {

    private val storage = CalculatorDatabase.getInstance(application).operationDao()
    private val calculatorLogic = CalculatorLogic(storage)
    var display: String = ""
    private var lastOperaton: String = ""
    private var listener: OnDisplayChanged? = null

    //private val storage = ListStorage.getInstance()
    private lateinit var historyViewModelInterface: HistoryViewModelInterface


    fun onclickSimbol(symbol: String)  {
        display = calculatorLogic.insertSymbol(display, symbol)
        notifyOnDisplayChanged()
    }

    fun onClickEquals()  {
        lastOperaton = display
        val result = calculatorLogic.performOperation(display)
        display = result.toString()
        notifyOnDisplayChanged()
    }

    fun getLastOperation(): String = "${lastOperaton} = ${display}"

    fun unregisterListener() {
      listener = null
    }
    fun registerListener(listener: OnDisplayChanged){
        this.listener = listener
        listener?.onDisplayChanged(display)
        historyViewModelInterface = object : HistoryViewModelInterface {
            override fun getAllHistory(values: List<Operation>) {
                listener.setHistoryList(values)
            }

        }
    }

    private fun notifyOnDisplayChanged(){
        listener?.onDisplayChanged(display)
    }

    fun updateAdapter() {
        Thread.sleep(5000)
        listener?.onAdapterChanged()
    }

    fun onItemClick(result: String) {
        listener?.onToastChanged(result)
    }

      fun longClickdeleteItem(id: String) {

              calculatorLogic.delete(id)
             listener?.onAdapterChanged()


      //
    }
    suspend  fun historyGetAll()  {
        listener?.setHistoryList(calculatorLogic.historyGetAll())
    }

}
