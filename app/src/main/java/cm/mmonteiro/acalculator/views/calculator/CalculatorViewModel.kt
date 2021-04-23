package cm.mmonteiro.acalculator.views.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import cm.mmonteiro.acalculator.R
import cm.mmonteiro.acalculator.adapters.HistoryAdapter
import cm.mmonteiro.acalculator.helpers.ListStorage
import cm.mmonteiro.acalculator.interfaces.HistoryInterface
import cm.mmonteiro.acalculator.interfaces.HistoryViewModelInterface
import cm.mmonteiro.acalculator.interfaces.OnDisplayChanged
import cm.mmonteiro.acalculator.models.Operation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel : ViewModel() {

    private val calculatorLogic = CalculatorLogic()
    var display: String = ""
    private var lastOperaton: String = ""
    private var listener: OnDisplayChanged? = null
    //var adapter : HistoryAdapter? = null
    private val storage = ListStorage.getInstance()
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
        storage.deleteItem(id)
        listener?.onAdapterChanged()
    }
    fun historyGetAll()  {
        CoroutineScope(Dispatchers.Main).launch{
            storage.getAll(historyViewModelInterface)
        }

    }
}
