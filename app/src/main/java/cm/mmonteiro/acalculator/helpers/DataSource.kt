package cm.mmonteiro.acalculator.helpers

import cm.mmonteiro.acalculator.models.Operation
import java.util.ArrayList

object DataSource {
    var operations = ArrayList<Operation>()
    private var instance: DataSource? = null


    fun addHistory(history:Operation){
        operations.add(history)
    }
    fun getHistory( ):ArrayList<Operation>{
        return operations
    }
}