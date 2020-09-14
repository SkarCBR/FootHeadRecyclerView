package com.mrskar.samples.huaweimaps.presentation

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.material.bottomsheet.BottomSheetDialog
import com.huawei.hms.maps.CameraUpdateFactory
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.MapFragment
import com.huawei.hms.maps.MapsInitializer
import com.huawei.hms.maps.OnMapReadyCallback
import com.huawei.hms.maps.model.LatLng
import com.huawei.hms.maps.model.Marker
import com.huawei.hms.maps.model.MarkerOptions
import com.mrskar.samples.R
import com.mrskar.samples.databinding.FragmentHuaweiMapsBinding
import com.mrskar.samples.widgets.BottomSheetDialogFragment

class FragmentHuaweiMaps : Fragment() {

    private val homeTitle = "Home"
    private val workTitle = "Work"

    private var _binding: FragmentHuaweiMapsBinding? = null
    private val binding get() = _binding!!

    private val callback = OnMapReadyCallback { huaweiMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        setInitialPosition(huaweiMap)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapsInitializer.setApiKey("CgB6e3x9t5TPC3sMfj5WMkWYKxJCTI7wHElX6ABr2OFQVXwR26EfgmU/h8/pas4IPvgsnM4HhgsLbRTGWGqY3i0/")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHuaweiMapsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.huawei_map) as MapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setInitialPosition(huaweiMap: HuaweiMap) {
        val home = LatLng(41.3972851, 2.1276549)
        val work = LatLng(41.3864328,2.1685704)
        huaweiMap.apply {
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