package com.mrskar.samples.recyclerview.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrskar.samples.R
import com.mrskar.samples.recyclerview.presentation.adapter.FootHeadAdapter
import com.mrskar.samples.recyclerview.presentation.model.ContentModel
import com.mrskar.samples.recyclerview.presentation.model.FootHeadItemContract
import com.mrskar.samples.vm.MainViewModel
import com.mrskar.samples.vm.getViewModelInstance
import kotlinx.android.synthetic.main.fragment_recycler_view.*

/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerViewFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = activity.getViewModelInstance(MainViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        main_recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FootHeadAdapter(listOf(), ::showContentAlert)
            setHasFixedSize(true)
        }

        viewModel.getItemsLiveData().observe(viewLifecycleOwner,
            Observer<List<FootHeadItemContract>> { items ->
                (main_recyclerview.adapter as FootHeadAdapter).setData(items)
            })
    }

    private fun showContentAlert(item: ContentModel) {
        context?.let {
            AlertDialog.Builder(it)
                .setTitle(item.text)
                .setMessage(item.description)
                .setPositiveButton("Ok") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RecyclerViewFragment.
         */
        @JvmStatic
        fun newInstance() =
            RecyclerViewFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}
