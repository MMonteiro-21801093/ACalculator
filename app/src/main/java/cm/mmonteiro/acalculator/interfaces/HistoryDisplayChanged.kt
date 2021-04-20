package cm.mmonteiro.acalculator.interfaces

import cm.mmonteiro.acalculator.adapters.HistoryAdapter
import cm.mmonteiro.acalculator.models.Operation


interface HistoryDisplayChanged {
    fun onDisplayChanged(value: String)
    fun onAdapterChanged(value: HistoryAdapter?)
    fun setHistoryList(values:List<Operation>)
}