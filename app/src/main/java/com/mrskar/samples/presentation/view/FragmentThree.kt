package com.mrskar.samples.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mrskar.samples.R
import com.mrskar.samples.presentation.view.custom.DetailBottomDialogFragment
import com.mrskar.samples.presentation.vm.MainViewModel
import com.mrskar.samples.presentation.vm.getViewModelInstance
import kotlinx.android.synthetic.main.fragment_three.*

class FragmentThree : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity.getViewModelInstance(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_three, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment3_show_button.setOnClickListener {
            DetailBottomDialogFragment().show(childFragmentManager, "bottomsheet")
        }
    }

}
