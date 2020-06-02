package com.mrskar.samples.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mrskar.samples.R
import com.mrskar.samples.presentation.vm.MainViewModel
import com.mrskar.samples.presentation.vm.getViewModelInstance
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val recyclerFragment =
        RecyclerViewFragment()
    private val mapFragment = MapsFragment()
    private val fragmentThree = FragmentThree()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = getViewModelInstance(MainViewModel::class.java)
        initView()
    }

    private fun initView() {
        replaceFragment(recyclerFragment)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.app_bar_recyclerview -> {
                    replaceFragment(recyclerFragment)
                    true
                }
                R.id.app_bar_map -> {
                    replaceFragment(mapFragment)
                    true
                }
                R.id.app_bar_search -> {
                    replaceFragment(fragmentThree)
                    true
                }
                else -> {
                    for (fragment in supportFragmentManager.fragments) {
                        supportFragmentManager.beginTransaction().remove(fragment).commit()
                    }
                    true
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }
}
