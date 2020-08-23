package com.mrskar.samples.map.presentation

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mrskar.samples.R
import com.mrskar.samples.widgets.BottomSheetDialogFragment

class FragmentHuaweiMaps : Fragment() {

    private val homeTitle = "Home"
    private val workTitle = "Work"

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        setInitialPosition(googleMap)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_huawei_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun setInitialPosition(googleMap: GoogleMap) {
        val home = LatLng(41.3972851, 2.1276549)
        val work = LatLng(41.3864328,2.1685704)
        googleMap.apply {
            addMarker(MarkerOptions().position(home).title(homeTitle))
            addMarker(MarkerOptions().position(work).title(workTitle))
            animateCamera(CameraUpdateFactory.newLatLngZoom(home, 12F))
            setOnMarkerClickListener { handleMarkerClick(it) }
        }
    }

    private fun handleMarkerClick(marker: Marker): Boolean {
        return when(marker.title) {
            homeTitle -> { showBottomSheetDialogFragment() }
            workTitle -> { showBottomSheetDialog() }
            else -> false
        }
    }

    private fun showBottomSheetDialogFragment(): Boolean {
        val bottomSheetFragment = BottomSheetDialogFragment()
        bottomSheetFragment.show(parentFragmentManager,bottomSheetFragment.tag)
        return true
    }

    private fun showBottomSheetDialog(): Boolean {
        context?.let {
            BottomSheetDialog(it).apply {
                setContentView(R.layout.bottom_sheet_layout)
            }.show()
        }
        return true
    }
}