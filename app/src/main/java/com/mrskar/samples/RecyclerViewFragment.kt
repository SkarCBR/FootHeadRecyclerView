package com.mrskar.samples

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrskar.samples.adapter.FootHeadAdapter
import com.mrskar.samples.model.ContentModel
import com.mrskar.samples.model.FootHeadItemContract
import com.mrskar.samples.vm.MainViewModel
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
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
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
        initView()
    }

    private fun initView() {
        main_recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FootHeadAdapter(listOf(), ::showContentAlert)
            setHasFixedSize(true)
        }

        viewModel.getItemsLiveData().observe(this,
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
