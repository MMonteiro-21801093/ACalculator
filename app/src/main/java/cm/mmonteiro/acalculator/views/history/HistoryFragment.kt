package cm.mmonteiro.acalculator.views.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cm.mmonteiro.acalculator.R
import cm.mmonteiro.acalculator.adapters.HistoryAdapter
import cm.mmonteiro.acalculator.interfaces.CalculatorInterface
import cm.mmonteiro.acalculator.interfaces.HistoryInterface
import cm.mmonteiro.acalculator.models.Operation
import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryFragment : Fragment(), CalculatorInterface {
    private lateinit var historyListener: HistoryInterface
    private lateinit var viewModel: HistoryViewModel
    private lateinit var adapter : HistoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     //   val strtext = arguments!!.getParcelableArrayList<Operation>(EXTRA_HISTORY)
        viewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        val view =inflater.inflate(R.layout.fragment_history, container, false)
       // viewModel.adapter.let { view.list_historic.adapter = it }
        return view
    }
    override fun onStart() {
        viewModel.registerListener(this)
        super.onStart()



            historyListener = object : HistoryInterface {
                override fun onItemClick(operation: Operation) {
                    viewModel.onItemClick(operation.expression)
                }

                override fun longClickdeleteItem(operation: Operation) {
                    viewModel.longClickdeleteItem(operation.uuid)
                }

            }



/*        historyListener = object : HistoryInterface {
            override fun onItemClick(result: String) {
                showToastMessage(result)
            }

        }*/

       // val operations = activity?.getIntent()?.getParcelableArrayListExtra<Operation>(EXTRA_HISTORY)

      list_historic.layoutManager = LinearLayoutManager(context as Context)


            viewModel.historyGetAll()







    }
    private fun showToastMessage(value: String) {
        Toast.makeText(
            context as Context,
            "$value",
            Toast.LENGTH_SHORT
        ).show()
    }
   override fun onAdapterChanged( ) {
       adapter.notifyDataSetChanged()
    }

    override fun setHistoryList(values:List<Operation>) {
        adapter =  HistoryAdapter(
            context as Context,
            R.layout.item_expression,
            values as MutableList<Operation>,
            historyListener)
        list_historic.adapter = adapter
    }

    override fun onDisplayChanged(value: String) {
        showToastMessage(value)
    }
    override fun onDestroy() {
        viewModel.unregisterListener()
        super.onDestroy()
    }
}