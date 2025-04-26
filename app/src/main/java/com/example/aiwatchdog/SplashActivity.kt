package com.example.aiwatchdog

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
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
        lifecycleScope.launch {
            delay(1700)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
} 