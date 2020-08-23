package com.mrskar.samples.googlemaps.presentation

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

class FragmentGoogleMaps : Fragment() {

    private val homeTitle = "Home"
    private val workTitle = "Work"
    private val home = LatLng(41.3972851, 2.1276549)
    private val work = LatLng(41.3864328,2.1685704)

    private var googleMap: GoogleMap? = null

    private val callback = OnMapReadyCallback { googleMap ->
        setUpMap(googleMap)
    }
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_google_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.fragment_three_map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        bottom_sheet_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        bottom_sheet_toolbar.setNavigationOnClickListener { hideBottomSheetView() }

        setUpBottomSheetLayout()
    }

    private fun setUpBottomSheetLayout() {
        bottomSheetBehavior =
            BottomSheetBehavior.from(bottom_sheet_container).apply {
                state = STATE_HIDDEN
                addBottomSheetCallback(object :
                    BottomSheetBehavior.BottomSheetCallback() {
                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        Log.d("SlideOffset: ", "$slideOffset")
                        //Slide OffSet = 1.0 TOP
                        //Slide OffSet = 0.0 MIDDLE
                        //Slide OffSet = -1.0 BOTTOM
                    }

                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        //handleBottomSheetState()
                    }
                })
                halfExpandedRatio = 0.6F
            }
    }

    private fun setUpMap(googleMap: GoogleMap) {
        googleMap.apply {
            addMarker(MarkerOptions().position(home).title(homeTitle))
            addMarker(MarkerOptions().position(work).title(workTitle))
            animateCamera(CameraUpdateFactory.newLatLngZoom(home, 12F))
            setOnMarkerClickListener {
                showBottomSheetView()
            }
            setOnMapClickListener { collapseBottomSheetView() }
        }
        this.googleMap = googleMap
    }

    private fun showBottomSheetView(): Boolean {
        bottomSheetBehavior.state = STATE_HALF_EXPANDED
        Log.d("Half Extended Ratio: ", "${bottomSheetBehavior.halfExpandedRatio}")
        return true
    }

    private fun collapseBottomSheetView(): Boolean {
        bottomSheetBehavior.state = STATE_COLLAPSED
        return true
    }

    private fun hideBottomSheetView() {
        bottomSheetBehavior.state = STATE_HIDDEN
    }

    private fun handleBottomSheetState() {
        when (bottomSheetBehavior.state) {
            STATE_EXPANDED -> {
                bottom_sheet_toolbar.visibility = View.VISIBLE
                Log.d("BottomSheet State: ", "State Expanded")
            }
            STATE_COLLAPSED -> {
                bottom_sheet_toolbar.visibility = View.GONE
                Log.d("BottomSheet State: ", "State Collapsed")
            }
            STATE_HALF_EXPANDED -> {
                bottom_sheet_toolbar.visibility = View.GONE
                Log.d("BottomSheet State: ", "State Half Expanded")
            }
            STATE_DRAGGING -> {
                Log.d("BottomSheet State: ", "State Dragging")
            }
            STATE_HIDDEN -> {
                bottom_sheet_toolbar.visibility = View.GONE
                Log.d("BottomSheet State: ", "State Hidden")
            }
        }
    }
}
