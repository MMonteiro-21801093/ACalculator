package cm.mmonteiro.acalculator.interfaces

import cm.mmonteiro.acalculator.adapters.HistoryAdapter

interface OnDisplayChanged {
    fun onDisplayChanged(value: String?)
    fun onToastChanged(value: String)
    fun onAdapterChanged(value: HistoryAdapter?)
}