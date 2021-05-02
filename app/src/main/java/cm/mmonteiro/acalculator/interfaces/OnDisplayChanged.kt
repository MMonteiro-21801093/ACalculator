package cm.mmonteiro.acalculator.interfaces

import cm.mmonteiro.acalculator.models.Operation

interface OnDisplayChanged {
    fun onDisplayChanged(value: String?)
    fun onToastChanged(value: String)
    fun onAdapterChanged()
    fun setHistoryList(values: MutableList<Operation>)
}