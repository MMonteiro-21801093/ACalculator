package cm.mmonteiro.acalculator.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*


class Operation(val expression:String,val result:Double){
    var uuid:String = UUID.randomUUID().toString()
}
