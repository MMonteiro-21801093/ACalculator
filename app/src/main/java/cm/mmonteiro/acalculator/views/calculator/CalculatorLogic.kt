package cm.mmonteiro.acalculator.views.calculator

import android.content.Context
import cm.mmonteiro.acalculator.R
import cm.mmonteiro.acalculator.adapters.HistoryAdapter
import cm.mmonteiro.acalculator.helpers.ListStorage
import cm.mmonteiro.acalculator.interfaces.HistoryInterface
import cm.mmonteiro.acalculator.models.Operation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorLogic {

    private val storage = ListStorage.getInstance()
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

    fun performOperation(expression: String): Double {
        val result = ExpressionBuilder(expression).build()
        val operation  = Operation(expression, result.evaluate())
        CoroutineScope(Dispatchers.IO).launch{
            storage.insert(operation)
        }
        return result.evaluate()
    }

}