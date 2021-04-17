package cm.mmonteiro.acalculator.views.calculator

import androidx.lifecycle.ViewModel
import cm.mmonteiro.acalculator.interfaces.OnDisplayChanged
import kotlinx.android.synthetic.main.fragment_calculator.*

class CalculatorViewModel : ViewModel() {

    private val calculatorLogic = CalculatorLogic()
    var display: String = ""
    private var lastOperaton: String = ""
    private var listener: OnDisplayChanged? = null

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

}
