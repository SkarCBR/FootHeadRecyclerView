package com.mrskar.samples

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrskar.samples.adapter.FootHeadAdapter
import com.mrskar.samples.model.ContentModel
import com.mrskar.samples.model.FootHeadItemContract
import com.mrskar.samples.vm.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initView()
    }

    private fun initView() {
        main_recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = FootHeadAdapter(listOf(), ::showContentAlert)
            setHasFixedSize(true)
        }

        viewModel.getItemsLiveData().observe(this,
            Observer<List<FootHeadItemContract>> { items ->
                (main_recyclerview.adapter as FootHeadAdapter).setData(items)
            })
    }

    private fun showContentAlert(item: ContentModel) {
        AlertDialog.Builder(this)
            .setTitle(item.text)
            .setMessage(item.description)
            .setPositiveButton("Ok") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
