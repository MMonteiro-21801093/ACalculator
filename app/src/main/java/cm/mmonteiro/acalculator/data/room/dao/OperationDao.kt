package cm.mmonteiro.acalculator.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cm.mmonteiro.acalculator.models.Operation

@Dao
interface OperationDao {

    @Insert
   suspend fun insert(operation: Operation)

   @Query("SELECT * FROM operation")
   suspend fun getAll():List<Operation>

    @Query("SELECT * FROM operation WHERE uuid = :uuid")
   suspend fun getById(uuid:String):Operation

    @Query("DELETE FROM operation WHERE uuid = :uuid")
    suspend fun delete(uuid:String)
}