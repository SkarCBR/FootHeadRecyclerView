package com.mrskar.samples

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrskar.samples.adapter.FootHeadAdapter
import com.mrskar.samples.model.ContentModel
import com.mrskar.samples.model.FooterModel
import com.mrskar.samples.model.HeaderModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val items = listOf(
        HeaderModel("Titulo 1"),
        ContentModel("Contenido 1", "Prueba con el contenido estatico"),
        ContentModel("Contenido 2", "Prueba con el contenido estatico",R.drawable.alternate_content_bg),
        ContentModel("Contenido 3", "Prueba con el contenido estatico"),
        ContentModel("Contenido 4", "Prueba con el contenido estatico",R.drawable.alternate_content_bg),
        ContentModel("Contenido 5", "Prueba con el contenido estatico"),
        ContentModel("Contenido 6", "Prueba con el contenido estatico",R.drawable.alternate_content_bg),
        ContentModel("Contenido 7", "Prueba con el contenido estatico"),
        ContentModel("Contenido 8", "Prueba con el contenido estatico",R.drawable.alternate_content_bg),
        ContentModel("Contenido 9", "Prueba con el contenido estatico"),
        ContentModel("Contenido 10", "Prueba con el contenido estatico",R.drawable.alternate_content_bg),
        HeaderModel("Titulo 2"),
        ContentModel("Contenido 11", "Prueba con el contenido estatico"),
        ContentModel("Contenido 12", "Prueba con el contenido estatico"),
        ContentModel("Contenido 13", "Prueba con el contenido estatico"),
        ContentModel("Contenido 14", "Prueba con el contenido estatico"),
        ContentModel("Contenido 15", "Prueba con el contenido estatico"),
        ContentModel("Contenido 16", "Prueba con el contenido estatico"),
        ContentModel("Contenido 17", "Prueba con el contenido estatico"),
        ContentModel("Contenido 18", "Prueba con el contenido estatico"),
        ContentModel("Contenido 19", "Prueba con el contenido estatico"),
        ContentModel("Contenido 20", "Prueba con el contenido estatico"),
        FooterModel("by Oscar Olivella")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        main_recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = FootHeadAdapter(items, ::showContentAlert)
            setHasFixedSize(true)
        }
    }

    private fun showContentAlert(item: ContentModel) {
        AlertDialog.Builder(this)
            .setTitle(item.text)
            .setMessage(item.description)
            .setPositiveButton("Ok") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
