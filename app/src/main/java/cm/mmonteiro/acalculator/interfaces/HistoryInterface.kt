package cm.mmonteiro.acalculator.interfaces

import cm.mmonteiro.acalculator.models.Operation

interface HistoryInterface {
    fun onItemClick(result: Operation)
    fun longClickdeleteItem(result: Operation)
}
