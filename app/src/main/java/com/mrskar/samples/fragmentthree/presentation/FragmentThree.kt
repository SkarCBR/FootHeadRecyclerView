package com.mrskar.samples.fragmentthree.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_DRAGGING
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HALF_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN
import com.mrskar.samples.R
import kotlinx.android.synthetic.main.bottom_sheet_layout.*

class FragmentThree : Fragment() {

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
        setUpMap(googleMap)
    }
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_three, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        bottomSheetBehavior =
            BottomSheetBehavior.from(bottom_sheet_container).apply {
                state = STATE_HIDDEN
                addBottomSheetCallback(object :
                    BottomSheetBehavior.BottomSheetCallback() {
                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        //TODO("Not yet implemented")
                    }

                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        handleBottomSheetState()
                    }

                })
            }
    }

    private fun setUpMap(googleMap: GoogleMap) {
        val home = LatLng(41.3972851, 2.1276549)
        googleMap.addMarker(MarkerOptions().position(home).title("Home"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(home, 12F))
        googleMap.setOnMarkerClickListener {
            showBottomSheetView()
            true
        }
    }

    private fun showBottomSheetView() {
        when (bottomSheetBehavior.state) {
            STATE_EXPANDED -> { bottomSheetBehavior.state = STATE_COLLAPSED }
            STATE_COLLAPSED -> { bottomSheetBehavior.state = STATE_EXPANDED }
            STATE_HIDDEN -> { bottomSheetBehavior.state = STATE_EXPANDED}
        }
    }

    private fun handleBottomSheetState() {
        when (bottomSheetBehavior.state) {
            STATE_EXPANDED -> { Log.d("BottomSheet State: ","State Expanded") }
            STATE_COLLAPSED -> { Log.d("BottomSheet State: ","State Collapsed") }
            STATE_HALF_EXPANDED -> { Log.d("BottomSheet State: ","State Half Expanded") }
            STATE_DRAGGING -> { Log.d("BottomSheet State: ","State Dragging") }
            STATE_HIDDEN -> { Log.d("BottomSheet State: ","State Hidden") }
        }
    }
}
