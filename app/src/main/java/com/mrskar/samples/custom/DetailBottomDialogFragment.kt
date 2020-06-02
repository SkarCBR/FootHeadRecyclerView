package com.mrskar.samples.custom

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mrskar.samples.R
import com.mrskar.samples.vm.MainViewModel
import com.mrskar.samples.vm.getViewModelInstance
import kotlinx.android.synthetic.main.bottom_sheet_layout.*

class DetailBottomDialogFragment: BottomSheetDialogFragment() {

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
        return inflater.inflate(R.layout.bottom_sheet_layout, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTestModelLiveData().observe(viewLifecycleOwner,
            Observer {
                bottom_sheet_textview.text = it.javaClass.simpleName
                    .plus("\n")
                    .plus(it.id.toString())
                    .plus("\n")
                    .plus(it.name)
                    .plus("\n")
                    .plus(it.list)
            })
        bottom_sheet_request_button.setOnClickListener { getData() }
    }

    private fun getData() {
        viewModel.requestTestData()
    }

    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }
}