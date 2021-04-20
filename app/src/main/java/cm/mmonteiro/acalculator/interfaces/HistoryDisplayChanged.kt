package cm.mmonteiro.acalculator.interfaces

import cm.mmonteiro.acalculator.adapters.HistoryAdapter

interface HistoryDisplayChanged {
    fun onDisplayChanged(value: String)
    fun onAdapterChanged(value: HistoryAdapter?)
}