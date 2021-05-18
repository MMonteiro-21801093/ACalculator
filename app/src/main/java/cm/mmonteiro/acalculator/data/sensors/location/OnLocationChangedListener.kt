package cm.mmonteiro.acalculator.data.sensors.location

import com.google.android.gms.location.LocationResult

interface OnLocationChangedListener {
    fun onLocationChangedListener(locationResult: LocationResult)
}