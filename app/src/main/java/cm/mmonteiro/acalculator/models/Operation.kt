package cm.mmonteiro.acalculator.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
data class Operation(val expression:String,val result:Double){
    @PrimaryKey
    var uuid:String = UUID.randomUUID().toString()
}

