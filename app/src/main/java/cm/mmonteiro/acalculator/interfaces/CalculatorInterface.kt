package cm.mmonteiro.acalculator.interfaces

import cm.mmonteiro.acalculator.models.Operation


interface CalculatorInterface {
    fun onDisplayChanged(value: String)
    fun onAdapterChanged()
    fun setHistoryList(values: MutableList<Operation>)
    fun onToastChanged(value: String)
}