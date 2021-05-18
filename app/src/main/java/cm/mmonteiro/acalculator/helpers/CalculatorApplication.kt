package cm.mmonteiro.acalculator.helpers

import android.app.Application
import cm.mmonteiro.acalculator.data.sensors.location.FusedLocation

class CalculatorApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FusedLocation.start(this)
    }
}