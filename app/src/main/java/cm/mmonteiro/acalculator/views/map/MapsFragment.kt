package cm.mmonteiro.acalculator.views.map

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cm.mmonteiro.acalculator.R
import cm.mmonteiro.acalculator.data.sensors.location.FusedLocation
import cm.mmonteiro.acalculator.data.sensors.location.OnLocationChangedListener
import cm.mmonteiro.acalculator.helpers.PermissionedFragment
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_map.view.*
import java.util.jar.Manifest

const val REQUEST_CODE = 100

class MapsFragment : PermissionedFragment(REQUEST_CODE), OnMapReadyCallback,
    OnLocationChangedListener {
    private var map: GoogleMap? = null

    override fun onRequestedPermissionsSucess() {
        FusedLocation.registerListener(this)
        map_view.getMapAsync(this)
        map_view.onResume()
    }

    override fun onRequestedPermissionsFailure() {
        Toast.makeText(
            context as Context,
            "Permissões negadas",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onStart() {

        super.onRequestedPermissions(activity?.baseContext!!, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION))
        super.onStart()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_map, container, false)
        view.map_view.onCreate(savedInstanceState)
        return view
    }


    override fun onMapReady(map: GoogleMap?) {
        this.map = map
    }

    override fun onLocationChangedListener(locationResult: LocationResult) {
         val location = locationResult.lastLocation
    }



}