package cm.mmonteiro.acalculator.views.map

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import cm.mmonteiro.acalculator.R
import cm.mmonteiro.acalculator.data.sensors.location.FusedLocation
import cm.mmonteiro.acalculator.data.sensors.location.OnLocationChangedListener
import cm.mmonteiro.acalculator.helpers.PermissionedFragment
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_map.view.*
import java.util.*


const val REQUEST_CODE = 100

class MapsFragment : PermissionedFragment(REQUEST_CODE), OnMapReadyCallback,
    OnLocationChangedListener {
    private var map: GoogleMap? = null
    private lateinit var viewModel: MapsViewModel
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
        viewModel.registerListener(this)
        super.onRequestedPermissions(
            activity?.baseContext!!, arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
        super.onStart()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(MapsViewModel::class.java)
        var view = inflater.inflate(R.layout.fragment_map, container, false)
        view.map_view.onCreate(savedInstanceState)
        return view
    }


    override fun onMapReady(map: GoogleMap?) {
        this.map = map
    }

    override fun onLocationChangedListener(locationResult: LocationResult) {
         val location = locationResult.lastLocation
        val gcd = Geocoder(context, Locale.getDefault())
        val addresses: List<Address> = gcd.getFromLocation(38.6446, -9.2334, 1)
        if (addresses.size > 0) {
             println(addresses[0].getLocality())
        } else {

        }
    }



}