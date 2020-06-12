package com.mrskar.samples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mrskar.samples.fragmentthree.presentation.FragmentThree
import com.mrskar.samples.map.presentation.MapsFragment
import com.mrskar.samples.recyclerview.presentation.RecyclerViewFragment
import com.mrskar.samples.vm.MainViewModel
import com.mrskar.samples.vm.getViewModelInstance
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val recyclerFragment =
        RecyclerViewFragment()
    private val mapFragment = MapsFragment()
    private val fragmentThree =
        FragmentThree()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = getViewModelInstance(MainViewModel::class.java)
        initView()
    }

    private fun initView() {
        replaceFragment(recyclerFragment)
        /*
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.recyclerViewFragment -> {
                    replaceFragment(recyclerFragment)
                    true
                }
                R.id.mapsFragment -> {
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
         */
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_nav_host_fragment, fragment)
            .commit()
    }
}
