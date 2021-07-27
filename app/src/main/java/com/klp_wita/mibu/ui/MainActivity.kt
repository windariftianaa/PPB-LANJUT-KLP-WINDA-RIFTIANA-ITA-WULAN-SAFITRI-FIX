package com.klp_wita.mibu.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.klp_wita.mibu.R
import com.klp_wita.mibu.ui.Profile.ProfileChangeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun switchFragment(fragment: Fragment, addToBackstack: Boolean){

        val mFragment = supportFragmentManager.fragments.lastOrNull()
        if (mFragment != null && fragment::class == mFragment::class) {
            return
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.FRProfileDetailInfo, fragment)
            .addToBackStack(null)
            .commit()
    }


}