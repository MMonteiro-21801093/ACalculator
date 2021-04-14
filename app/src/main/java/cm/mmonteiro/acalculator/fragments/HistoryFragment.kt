package cm.mmonteiro.acalculator.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cm.mmonteiro.acalculator.R
import cm.mmonteiro.acalculator.helpers.DataSource
import cm.mmonteiro.acalculator.adapters.HistoryAdapter
import cm.mmonteiro.acalculator.interfaces.HistoryInterface
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_calculator.*

class HistoryFragment : Fragment() {
    private lateinit var historyListener: HistoryInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     //   val strtext = arguments!!.getParcelableArrayList<Operation>(EXTRA_HISTORY)
        return inflater.inflate(R.layout.fragment_history, container, false)
    }
    override fun onStart() {
        super.onStart()
        historyListener = object : HistoryInterface {
            override fun onItemClick(result: String) {
                showToastMessage(result)
            }

        }

       // val operations = activity?.getIntent()?.getParcelableArrayListExtra<Operation>(EXTRA_HISTORY)
        val db = DataSource
        list_historic.layoutManager = LinearLayoutManager(context as Context)
        list_historic.adapter = HistoryAdapter(
            context as Context,
            R.layout.item_expression,
            db.getHistory()!!,
            historyListener
        )


    }
    private fun showToastMessage(result: String) {
        Toast.makeText(
            context as Context,
            "$result",
            Toast.LENGTH_SHORT
        ).show()
    }
}