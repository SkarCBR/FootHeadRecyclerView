package com.mrskar.samples.widgets

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mrskar.samples.R
import com.mrskar.samples.vm.MainViewModel
import com.mrskar.samples.vm.getViewModelInstance

class BottomSheetFragment: BottomSheetDialogFragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity.getViewModelInstance(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        viewModel.getTestModelLiveData().observe(viewLifecycleOwner,
            Observer {})
    }

    private fun setUpView() {
        // Set dialog to full screen
        dialog?.setOnShowListener { dialog ->
            val bottomSheetInteral =
                (dialog as BottomSheetDialog).findViewById<View>(R.id.bottom_sheet_container)
            bottomSheetInteral?.minimumHeight = Resources.getSystem().displayMetrics.heightPixels
        }
    }

    private fun getData() {
        viewModel.requestTestData()
    }
}