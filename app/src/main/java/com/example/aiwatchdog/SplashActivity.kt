package com.example.aiwatchdog

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.aiwatchdog.ui.IncidentListFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.hide()
        }
//        if (savedInstanceState == null) {
//            switchFragment(IncidentListFragment())
//        }
        lifecycleScope.launch {
            delay(1700)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

//    private fun switchFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragmentContainer, fragment)
//            .commit()
//    }
} 