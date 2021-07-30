package com.klp_wita.mibu.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.klp_wita.mibu.R

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        auth = FirebaseAuth.getInstance()
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_buy,R.id.navigation_profile
        ))
        val currentUser = auth.currentUser
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun switchFragment(fragment: Fragment, addToBackstack: Boolean){

        val mFragment = supportFragmentManager.fragments.lastOrNull()
        if (mFragment != null && fragment::class == mFragment::class) {
            return
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fr_container_profileDetails,fragment)
            .commit()
    }


}