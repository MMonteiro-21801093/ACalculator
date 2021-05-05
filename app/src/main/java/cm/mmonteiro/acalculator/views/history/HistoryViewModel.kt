package cm.mmonteiro.acalculator.views.history

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import cm.mmonteiro.acalculator.data.repositories.OperationRepository
import cm.mmonteiro.acalculator.data.repositories.RemoteCalculator

import cm.mmonteiro.acalculator.data.room.CalculatorDatabase
import cm.mmonteiro.acalculator.domain.CalculatorLogic
import cm.mmonteiro.acalculator.helpers.Constants
import cm.mmonteiro.acalculator.interfaces.CalculatorInterface
import cm.mmonteiro.acalculator.interfaces.HistoryViewModelInterface
import cm.mmonteiro.acalculator.models.Operation
import cm.mmonteiro.acalculator.remote.RetrofitBuilder

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HistoryViewModel(application: Application) : AndroidViewModel(application)  {
     //private val storage = ListStorage.getInstance()
    private val storage = CalculatorDatabase.getInstance(application).operationDao()
    val constants = Constants.getInstance()
    val remoteCalculator = RemoteCalculator(storage,RetrofitBuilder.getInstance(constants.ENDPOINT))
    private val operationRepository = OperationRepository(remoteCalculator)
     private var listener: CalculatorInterface? = null
    private lateinit var historyViewModelInterface: HistoryViewModelInterface


    fun unregisterListener() {
        listener = null
    }
    fun registerListener(
        listener: CalculatorInterface,
    ){
        this.listener = listener
       // listener?.onAdapterChanged(adapter)

        historyViewModelInterface = object : HistoryViewModelInterface {
            override fun getAllHistory(values: MutableList<Operation>) {
                CoroutineScope(Dispatchers.Main).launch{
                    listener.setHistoryList(values)
                }

            }

            override fun returnMessage(message: String) {
                CoroutineScope(Dispatchers.Main).launch {
                    listener?.onToastChanged(message)

                    //historyViewModelInterface.getAllHistory(storage)
                }
            }

        }
    }

    private fun notifyOnDisplayChanged(){
        listener?.onAdapterChanged()
    }

    fun onItemClick(result: String) {
        listener?.onDisplayChanged(result)
    }

    fun longClickdeleteItem(context: Context) {
    /*    CoroutineScope(Dispatchers.IO).launch{
        calculatorLogic.delete(id,historyViewModelInterface)
        }*/
        operationRepository.deleteAll(historyViewModelInterface,context)
    }

       fun historyGetAll(context: Context)  {
           operationRepository.getAll(historyViewModelInterface, context)
    }


}