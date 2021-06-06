package com.example.garda

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity


class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        
        Handler(Looper.getMainLooper()).postDelayed(
            {
            val n = Intent(this, MainActivity::class.java)
            startActivity(n)
            finish()
        }, 4500)
    }
}