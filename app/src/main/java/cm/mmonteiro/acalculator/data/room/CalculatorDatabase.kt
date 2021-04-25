package cm.mmonteiro.acalculator.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cm.mmonteiro.acalculator.data.list.ListStorage
import cm.mmonteiro.acalculator.data.room.dao.OperationDao
import cm.mmonteiro.acalculator.models.Operation

@Database(entities = arrayOf(Operation::class), version = 1)
abstract class CalculatorDatabase : RoomDatabase() {

    abstract fun operationDao(): OperationDao

    companion object {
        private var instance: CalculatorDatabase? = null
        fun getInstance(applicationContext: Context): CalculatorDatabase {
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        applicationContext, CalculatorDatabase::class.java,
                        "calculator_db"
                    ).build()
                }
                return  instance as CalculatorDatabase
            }
        }
    }

}