package com.example.garda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        img_screen.alpha=0f
        img_screen.animate().setDuration(100).alpha(1f).withEndAction{
            val n = Intent(this, MainActivity::class.java)
            startActivity(n)
            finish()
        }
    }
}