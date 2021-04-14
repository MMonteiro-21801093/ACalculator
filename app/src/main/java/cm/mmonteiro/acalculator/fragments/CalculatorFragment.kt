package cm.mmonteiro.acalculator.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import cm.mmonteiro.acalculator.R
import cm.mmonteiro.acalculator.activities.MainActivity
import cm.mmonteiro.acalculator.helpers.DataSource
import cm.mmonteiro.acalculator.adapters.HistoryAdapter
import cm.mmonteiro.acalculator.interfaces.HistoryInterface
import cm.mmonteiro.acalculator.models.Operation

import kotlinx.android.synthetic.main.fragment_calculator.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.SimpleDateFormat
import java.util.*

class CalculatorFragment : Fragment() {
    private val VISOR_KEY = "visor"
    private val TAG = MainActivity::class.java.simpleName

    lateinit  var adapter : HistoryAdapter
    private lateinit var historyListener: HistoryInterface
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val view =inflater.inflate(R.layout.fragment_calculator, container, false)
        ButterKnife.bind(this, view)
       return view
    }

    override fun onStart() {
        super.onStart()


        val configuration: Configuration = resources.configuration
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            historyListener = object : HistoryInterface {
                override fun onItemClick(result: String) {
                    Toast.makeText(
                        activity as Context,
                        "$result",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            val db = DataSource

            adapter = HistoryAdapter(
                activity as Context,
                R.layout.item_expression,
                db.getHistory(),
                historyListener
            )
            list_historic.layoutManager = LinearLayoutManager(activity as Context)
            list_historic.adapter =   adapter


        }
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

   // @Optional(R.id.button_00)
    @OnClick(
        R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
        R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9,
        R.id.button_ce, R.id.button_backspace, R.id.button_multi, R.id.button_adition,
        R.id.button_subtraction, R.id.button_0, R.id.button_dot
    )
    fun onclickSimbol(view: View) {

        val symbol = view.tag.toString()
        when (symbol) {
            "CE" -> text_visor.text = ""
            "<" -> text_visor.text = text_visor.text.substring(0, text_visor.length() - 1)
            in "0".."9" -> {
                if (text_visor.text == "0") {
                    text_visor.text = symbol
                } else {
                    text_visor.append(symbol)
                }
            }
            else -> text_visor.append(symbol)
        }
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
            val expression = text_visor.text.toString()
            val result = ExpressionBuilder(text_visor.text.toString()).build()

            text_visor.text = result.evaluate().toString()

            val operation:Operation = Operation(expression, result.evaluate())
            val db = DataSource
            db.addHistory(operation)
            val configuration: Configuration = resources.configuration
            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                adapter.notifyDataSetChanged()
            }else{
                text_last_calc.text = "${expression} = ${result.evaluate()}"
            }

            Log.i(TAG, "O resultado da expressão é ${text_visor.text}")
            formatToastDate("=")
        }
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