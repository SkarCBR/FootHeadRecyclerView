package com.mrskar.samples.bottomsheet

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_DRAGGING
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HALF_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_SETTLING
import com.mrskar.samples.R
import kotlinx.android.synthetic.main.fragment_maps.*

class BottomSheetTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_maps)

        configBottomSheet()
    }

    private fun configBottomSheet() {
        val standardBottomSheetBehaviour = BottomSheetBehavior.from(standardBottomSheet)
        val callback = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                bottomsheet_state.text = getState(newState)
            }
        }
        standardBottomSheetBehaviour.apply {
            isHideable = true
            peekHeight = 100
            state = STATE_COLLAPSED
            setBottomSheetCallback(callback)
        }
    }

    private fun getState(state: Int): String {
        return when(state) {
            STATE_COLLAPSED -> "Collapsed"
            STATE_HALF_EXPANDED -> "Half Expanded"
            STATE_EXPANDED -> "Expanded"
            STATE_HIDDEN -> "Hidden"
            STATE_DRAGGING -> "Dragging"
            STATE_SETTLING -> "Settling"
            else -> "No state"
        }
    }
}