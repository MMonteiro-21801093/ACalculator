package cm.mmonteiro.acalculator.views.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import cm.mmonteiro.acalculator.data.sensors.location.OnLocationChangedListener
import cm.mmonteiro.acalculator.interfaces.CalculatorInterface

class MapsViewModel(application: Application) : AndroidViewModel(application)  {

    private var listener: OnLocationChangedListener? = null

    fun registerListener(OnLocationChangedListener: OnLocationChangedListener) {
        listener = OnLocationChangedListener
    }


}