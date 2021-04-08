package cm.mmonteiro.acalculator.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Operation(val expression:String,val result:Double) :Parcelable
