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
import cm.mmonteiro.acalculator.interfaces.HistoryDisplayChanged
import cm.mmonteiro.acalculator.interfaces.HistoryInterface
import cm.mmonteiro.acalculator.views.calculator.CalculatorViewModel
import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.android.synthetic.main.fragment_history.view.*

class HistoryFragment : Fragment(), HistoryDisplayChanged {
    private lateinit var historyListener: HistoryInterface
    private lateinit var viewModel: HistoryViewModel
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

/*        historyListener = object : HistoryInterface {
            override fun onItemClick(result: String) {
                showToastMessage(result)
            }

        }*/

       // val operations = activity?.getIntent()?.getParcelableArrayListExtra<Operation>(EXTRA_HISTORY)

        list_historic.layoutManager = LinearLayoutManager(context as Context)
        viewModel.historyAdapter(context as Context)
    //    list_historic.adapter = viewModel.historyAdapter(context as Context)

    }
    private fun showToastMessage(value: String) {
        Toast.makeText(
            context as Context,
            "$value",
            Toast.LENGTH_SHORT
        ).show()
    }
    override fun onAdapterChanged(value: HistoryAdapter?) {
        value.let{ list_historic.adapter = it}
    }
    override fun onDisplayChanged(value: String) {
        showToastMessage(value)
    }
    override fun onDestroy() {
        viewModel.unregisterListener()
        super.onDestroy()
    }
}