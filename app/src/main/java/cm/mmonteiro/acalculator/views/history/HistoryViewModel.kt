package cm.mmonteiro.acalculator.views.history

import android.content.Context
import androidx.lifecycle.ViewModel
import cm.mmonteiro.acalculator.R
import cm.mmonteiro.acalculator.adapters.HistoryAdapter
import cm.mmonteiro.acalculator.helpers.ListStorage
import cm.mmonteiro.acalculator.interfaces.HistoryDisplayChanged
import cm.mmonteiro.acalculator.interfaces.HistoryInterface
import cm.mmonteiro.acalculator.interfaces.OnDisplayChanged
import cm.mmonteiro.acalculator.models.Operation


class HistoryViewModel : ViewModel() {

    private lateinit var historyListener: HistoryInterface
    private val storage = ListStorage.getInstance()
       var adapter : HistoryAdapter? = null
      private var listener: HistoryDisplayChanged? = null

    fun historyAdapter(context: Context)  {
        historyListener = object : HistoryInterface {
            override fun onItemClick(result: String) {
               listener?.onDisplayChanged(result)
            }

            override fun longClickdeleteItem(id: String) {
                storage.deleteItem(id)
                adapter?.notifyDataSetChanged()
                notifyOnDisplayChanged()
            }

        }
        adapter = HistoryAdapter(
            context,
            R.layout.item_expression,
            storage.getAll() as MutableList<Operation>,
            historyListener
        )
        notifyOnDisplayChanged()
    }

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
}