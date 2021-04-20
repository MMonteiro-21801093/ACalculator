package cm.mmonteiro.acalculator.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cm.mmonteiro.acalculator.interfaces.HistoryInterface
import cm.mmonteiro.acalculator.models.Operation
import kotlinx.android.synthetic.main.item_expression.view.*


class HistoryAdapter(
    private val context: Context, private val layout: Int, private val items:
    MutableList<Operation>, private val histListener: HistoryInterface
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val expression: TextView = view.text_expression
        val result: TextView = view.text_result

    }


    /*  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
          val view = convertView ?: LayoutInflater.from(context).inflate(layout,parent,false)
          view.text_expression.text = getItem(position)?.expression
          view.text_result.text = getItem(position)?.result.toString()
          return view
      }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(LayoutInflater.from(context).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.expression.text = items[position].expression
        holder.result.text = items[position].result.toString()
        holder.itemView.setOnClickListener {
            histListener.onItemClick(items[position])
        }
        holder.itemView.setOnLongClickListener {
            histListener.longClickdeleteItem(items[position])
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount() = items.size


}