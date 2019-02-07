package company.inside.sangue.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import company.inside.sangue.R
import company.inside.sangue.ui.core.BaseFragment
import company.inside.sangue.ui.donator.register.RegisterDonatorFragment
import company.inside.sangue.ui.receptor.register.RegisterReceptorFragment
import company.inside.sangue.util.OpenManageClass

class MainStreamFragment : BaseFragment(), OnMapReadyCallback {


    lateinit var btnDonator:Button
    lateinit var btnReceptor:Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_stream, container, false)
        this.findViews(view)
        this.onClickActions()
        this.createMaps()
        return view;
    }

    fun findViews(view: View) {
        btnDonator = view.findViewById(R.id.btn_donator)
        btnReceptor = view.findViewById(R.id.btn_receptor)
    }

    //region Setup Maps
    /****
     * create Maps
     */
    fun createMaps() {
        val mapFragment =  requireActivity().supportFragmentManager?.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(gMaps: GoogleMap) {
        makersPosition(gMaps);
    }

    /***
     * Marker in position
     */
    fun makersPosition( gMaps:GoogleMap) {
        val latitude = -30.062784
        val longitude = -51.1806828

        gMaps.addMarker(MarkerOptions().position(LatLng(latitude ,longitude)).title("Hemocentro POA"))

        //zoom in position
        gMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude, longitude), 15f))
    }
    //endregion

    fun onClickActions() {
        btnDonator.setOnClickListener {
            OpenManageClass.openFragment(RegisterDonatorFragment(), activity?.supportFragmentManager!!,false)
        }

        btnReceptor.setOnClickListener {
            OpenManageClass.openFragment(RegisterReceptorFragment(),activity?.supportFragmentManager!!,false)
        }
    }

}
