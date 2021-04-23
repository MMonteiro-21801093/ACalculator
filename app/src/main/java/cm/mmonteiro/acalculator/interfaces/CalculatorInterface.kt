package cm.mmonteiro.acalculator.interfaces

import cm.mmonteiro.acalculator.adapters.HistoryAdapter
import cm.mmonteiro.acalculator.models.Operation


interface CalculatorInterface {
    fun onDisplayChanged(value: String)
    fun onAdapterChanged()
    fun setHistoryList(values:List<Operation>)
}