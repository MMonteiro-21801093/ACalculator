package cm.mmonteiro.acalculator.views.history

import androidx.lifecycle.ViewModel
import cm.mmonteiro.acalculator.adapters.HistoryAdapter
import cm.mmonteiro.acalculator.helpers.ListStorage
import cm.mmonteiro.acalculator.interfaces.HistoryDisplayChanged
import cm.mmonteiro.acalculator.interfaces.HistoryVMInterface
import cm.mmonteiro.acalculator.models.Operation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HistoryViewModel : ViewModel(), HistoryVMInterface {


     private val storage = ListStorage.getInstance()
     var adapter : HistoryAdapter? = null
      private var listener: HistoryDisplayChanged? = null
     private lateinit var historyVMInterface: HistoryVMInterface


    fun unregisterListener() {
        listener = null
    }
    fun registerListener(listener: HistoryDisplayChanged){
        this.listener = listener
        listener?.onAdapterChanged(adapter)
    }

    private fun notifyOnDisplayChanged(){
        listener?.onAdapterChanged(adapter)
    }

    fun onItemClick(result: String) {
        listener?.onDisplayChanged(result)
    }

    fun longClickdeleteItem(id: String) {
        storage.deleteItem(id)
        adapter?.notifyDataSetChanged()
        notifyOnDisplayChanged()
    }

    fun historyGetAll()  {
        CoroutineScope(Dispatchers.IO).launch{
            storage.getAll(historyVMInterface)
        }
    }

    override fun getAllHistory(values: List<Operation>) {
        notifyOnDisplayChanged()
    }
}