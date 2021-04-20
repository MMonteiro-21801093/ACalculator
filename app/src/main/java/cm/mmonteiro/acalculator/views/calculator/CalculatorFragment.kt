package cm.mmonteiro.acalculator.views.calculator

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import cm.mmonteiro.acalculator.R
import cm.mmonteiro.acalculator.activities.MainActivity
import cm.mmonteiro.acalculator.helpers.ListStorage
import cm.mmonteiro.acalculator.adapters.HistoryAdapter
import cm.mmonteiro.acalculator.interfaces.HistoryInterface
import cm.mmonteiro.acalculator.interfaces.OnDisplayChanged
import cm.mmonteiro.acalculator.models.Operation

import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.android.synthetic.main.fragment_calculator.view.*
import java.text.SimpleDateFormat
import java.util.*

class CalculatorFragment : Fragment(), OnDisplayChanged {
    private val TAG = MainActivity::class.java.simpleName
    private lateinit var viewModel: CalculatorViewModel

    private lateinit var historyListener: HistoryInterface
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)

       val view =inflater.inflate(R.layout.fragment_calculator, container, false)
     //   viewModel.display.let { view.text_visor.text = it }
        ButterKnife.bind(this, view)
       return view
    }

    override fun onStart() {
        viewModel.registerListener(this)
        super.onStart()


        val configuration: Configuration = resources.configuration
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {




          list_historic.layoutManager = LinearLayoutManager(activity as Context)
           viewModel.historyAdapter(activity as Context)
            //     list_historic.adapter =   viewModel.historyAdapter(activity as Context)


        }
    }
    private fun showToastMessage(value: String) {
        Toast.makeText(
            context as Context,
            "$value",
            Toast.LENGTH_SHORT
        ).show()
    }
    private fun formatToastDate(symbol: String) {
        val date = Calendar.getInstance().time
        var dateTimeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        Toast.makeText(
            activity as Context,
            "button$symbol.setOnClickListener ${dateTimeFormat.format(date)}",
            Toast.LENGTH_SHORT
        ).show()
    }

    @Optional
    @OnClick(
        R.id.button_00,
        R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
        R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9,
        R.id.button_ce, R.id.button_backspace, R.id.button_multi, R.id.button_adition,
        R.id.button_subtraction, R.id.button_0, R.id.button_dot
    )
    fun onclickSimbol(view: View) {
        val symbol = view.tag.toString()
        viewModel.onclickSimbol(symbol)
        Log.i(TAG, "Click no botão $symbol")
        formatToastDate(symbol)
    }

    @OnClick(R.id.button_equals)
    fun onclickEquals(view: View) {
        if (text_visor.text.endsWith("+") || text_visor.text.endsWith("-")
            || text_visor.text.endsWith("*") || text_visor.text.endsWith(".") ||
            text_visor.text.endsWith("/") || text_visor.text.endsWith("%")
        ) {
            Toast.makeText(
                activity as Context,
                "expressão inválida",
                Toast.LENGTH_SHORT
            ).show()
        } else {


           viewModel.onClickEquals()


            val configuration: Configuration = resources.configuration
            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                viewModel.updateAdapter()
            }else{
                text_last_calc.text = viewModel.getLastOperation()
            }

            Log.i(TAG, "O resultado da expressão é ${text_visor.text}")
            formatToastDate("=")
        }
    }

    override fun onDisplayChanged(value: String?) {
       value.let{text_visor.text = it}
    }

    override fun onToastChanged(value: String) {
        showToastMessage(value)
    }

    override fun onAdapterChanged(value: HistoryAdapter?) {
        value.let{ list_historic.adapter = it}
    }

    override fun onDestroy() {
        viewModel.unregisterListener()
        super.onDestroy()
    }

/*    fun onclickHistory(view: View) {
        val bundle = Bundle()
        bundle.putParcelableArrayList(EXTRA_HISTORY,ArrayList(operations))
        val fragobj = HistoryFragment()
        fragobj.setArguments(bundle)
        getFragmentManager()?.beginTransaction()
            ?.replace(R.id.frame, fragobj )
            ?.commit();
// Set Fragmentclass Arguments

// Set Fragmentclass Arguments

 *//*       val intent = Intent(this, HistoryActivity::class.java)
        intent.apply { putParcelableArrayListExtra(EXTRA_HISTORY,ArrayList(operations)) }
        startActivity(intent)
        finish()*//*

    }*/


}