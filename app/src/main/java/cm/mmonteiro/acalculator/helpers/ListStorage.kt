package cm.mmonteiro.acalculator.helpers

import cm.mmonteiro.acalculator.interfaces.HistoryViewModelInterface
import cm.mmonteiro.acalculator.models.Operation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListStorage {
    var storage = mutableListOf<Operation>()


 companion object{
     private var instance: ListStorage? = null

     fun getInstance():ListStorage{
         synchronized(this){
             if(instance== null){
                 instance = ListStorage()
             }
             return instance as ListStorage
         }
     }
 }
   suspend fun insert(operation:Operation){
        withContext(Dispatchers.IO){
            Thread.sleep(5000)
            storage.add(operation)
        }
    }
    suspend fun getAll(historyViewModelInterface: HistoryViewModelInterface) {
        withContext(Dispatchers.Main){
            Thread.sleep(5000)
            historyViewModelInterface.getAllHistory(storage)
        }

    }



    fun deleteItem(id: String) {
       for( item in storage){
           if(item.uuid== id){
               storage.remove(item)
               break
           }
       }
    }


}