package com.mrskar.samples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mrskar.samples.vm.MainViewModel
import com.mrskar.samples.vm.getViewModelInstance

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = getViewModelInstance(MainViewModel::class.java)

        if (savedInstanceState == null) {
            setupNavController()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupNavController()
    }

    private fun setupNavController() {
        val navController = findNavController(R.id.main_nav_host_fragment)
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            .setupWithNavController(navController)
    }
}
