package cm.mmonteiro.acalculator.interfaces

import cm.mmonteiro.acalculator.models.Operation

interface HistoryVMInterface {
    fun getAllHistory(values:List<Operation>)
}