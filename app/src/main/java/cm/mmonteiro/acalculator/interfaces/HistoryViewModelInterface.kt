package cm.mmonteiro.acalculator.interfaces

import cm.mmonteiro.acalculator.models.Operation

interface HistoryViewModelInterface {
    fun getAllHistory(values:List<Operation>)
}