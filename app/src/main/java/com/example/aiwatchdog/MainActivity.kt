package com.example.aiwatchdog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.aiwatchdog.ui.IncidentListFragment // Alerts tab
import com.example.aiwatchdog.ui.AddIncidentFragment   // Create Alert tab
import com.example.aiwatchdog.ui.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val actionBar = supportActionBar
//        if (actionBar != null) {
//            actionBar.hide()
//        }
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> switchFragment(IncidentListFragment())
                R.id.nav_alerts -> switchFragment(HomeFragment())
                R.id.nav_create -> switchFragment(AddIncidentFragment())
            }
            true
        }
        // Set default fragment
        if (savedInstanceState == null) {
            bottomNav.selectedItemId = R.id.nav_home
        }
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()

        val title = when (fragment) {
            is IncidentListFragment -> "Incidents"
            is AddIncidentFragment -> "Create Alert"
            // Add more fragments as needed
            else -> "AIWatchdog"
        }
        supportActionBar?.title = title
    }
}