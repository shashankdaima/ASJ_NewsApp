package com.example.androidstudyjam1.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.androidstudyjam1.R
import com.example.androidstudyjam1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<ActivityViewModel>()
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setSupportActionBar(binding.actionBar)
        setupActionBarWithNavController(navController)
    }

    fun hideNoInternetRibbon() {
        binding.noInternetRibbon.visibility = View.GONE
    }

    fun showNoInternetRibbon() {
        binding.noInternetRibbon.visibility = View.VISIBLE
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    companion object {
        private val TAG = "MainActivity"
    }
}