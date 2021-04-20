package cm.mmonteiro.acalculator.views.calculator

import android.content.Context
import androidx.lifecycle.ViewModel
import cm.mmonteiro.acalculator.R
import cm.mmonteiro.acalculator.adapters.HistoryAdapter
import cm.mmonteiro.acalculator.helpers.ListStorage
import cm.mmonteiro.acalculator.interfaces.HistoryInterface
import cm.mmonteiro.acalculator.interfaces.OnDisplayChanged
import cm.mmonteiro.acalculator.models.Operation

class CalculatorViewModel : ViewModel() {

    private val calculatorLogic = CalculatorLogic()
    var display: String = ""
    private var lastOperaton: String = ""
    private var listener: OnDisplayChanged? = null


    private lateinit var historyListener: HistoryInterface
    private val storage = ListStorage.getInstance()
    var adapter : HistoryAdapter? = null


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
    }

    private fun notifyOnDisplayChanged(){
        listener?.onDisplayChanged(display)
    }


    fun historyAdapter(context: Context)  {
        historyListener = object : HistoryInterface {
            override fun onItemClick(operation: Operation) {
                listener?.onToastChanged(operation.expression)
            }

            override fun longClickdeleteItem(operation: Operation) {
                storage.deleteItem(operation.uuid)
                adapter?.notifyDataSetChanged()
                notifyOnAdapterChanged()
            }

        }
 /*       adapter = HistoryAdapter(
            context,
            R.layout.item_expression,
            storage.getAll(historyVMInterface) as MutableList<Operation>,
            historyListener
        )*/
        notifyOnAdapterChanged()
    }

    private fun notifyOnAdapterChanged() {
        listener?.onAdapterChanged(adapter)
    }

    fun updateAdapter() {
            adapter?.notifyDataSetChanged()
            notifyOnAdapterChanged()
    }


}
