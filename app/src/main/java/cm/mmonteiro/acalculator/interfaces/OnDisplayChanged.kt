package cm.mmonteiro.acalculator.interfaces

import cm.mmonteiro.acalculator.adapters.HistoryAdapter
import cm.mmonteiro.acalculator.models.Operation

interface OnDisplayChanged {
    fun onDisplayChanged(value: String?)
    fun onToastChanged(value: String)
    fun onAdapterChanged()
    fun setHistoryList(values:List<Operation>)
}