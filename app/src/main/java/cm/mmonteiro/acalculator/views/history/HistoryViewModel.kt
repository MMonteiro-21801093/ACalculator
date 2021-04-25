package cm.mmonteiro.acalculator.views.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import cm.mmonteiro.acalculator.data.list.ListStorage
import cm.mmonteiro.acalculator.data.room.CalculatorDatabase
import cm.mmonteiro.acalculator.interfaces.CalculatorInterface
import cm.mmonteiro.acalculator.interfaces.HistoryViewModelInterface
import cm.mmonteiro.acalculator.models.Operation
import cm.mmonteiro.acalculator.views.calculator.CalculatorLogic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HistoryViewModel(application: Application) : AndroidViewModel(application)  {
     //private val storage = ListStorage.getInstance()
    private val storage = CalculatorDatabase.getInstance(application).operationDao()
    private val calculatorLogic = CalculatorLogic(storage)
     private var listener: CalculatorInterface? = null
    // private lateinit var historyViewModelInterface: HistoryViewModelInterface


    fun unregisterListener() {
        listener = null
    }
    fun registerListener(
        listener: CalculatorInterface,
    ){
        this.listener = listener
       // listener?.onAdapterChanged(adapter)

     /*   historyViewModelInterface = object : HistoryViewModelInterface {
            override fun getAllHistory(values: List<Operation>) {
                listener.setHistoryList(values)
            }

        }*/
    }

    private fun notifyOnDisplayChanged(){
        listener?.onAdapterChanged()
    }

    fun onItemClick(result: String) {
        listener?.onDisplayChanged(result)
    }

    fun longClickdeleteItem(id: String) {
        calculatorLogic.delete(id)
        notifyOnDisplayChanged()
    }

    fun historyGetAll()  {
        CoroutineScope(Dispatchers.Main).launch{
            listener?.setHistoryList(calculatorLogic.historyGetAll())
        }

    }


}