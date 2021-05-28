package com.example.garda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class SearchActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var ic_home : ImageButton
    private lateinit var ic_camera : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        ic_home = findViewById(R.id.ic_home)
        ic_home.setOnClickListener(this)

        ic_camera = findViewById(R.id.ic_camera)
        ic_camera.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ic_camera -> run {
                val mIntent = Intent(this, CameraActivity::class.java)
                startActivity(mIntent)
            }
            R.id.ic_camera -> run {
                val mIntent = Intent(this, CameraActivity::class.java)
                startActivity(mIntent)
            }
        }
    }
}