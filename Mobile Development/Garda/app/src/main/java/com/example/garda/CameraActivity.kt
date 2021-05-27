package com.example.garda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var ic_home : ImageButton
    private lateinit var ic_search : ImageButton

    companion object {
        const val REQUEST_FROM_CAMERA = 1001;
        const val REQUEST_FROM_GALLERY = 1002;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        ic_home = findViewById(R.id.ic_home)
        ic_home.setOnClickListener(this)

        ic_search = findViewById(R.id.ic_search)
        ic_search.setOnClickListener(this)

        initUI()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ic_home -> run {
                val mIntent = Intent(this, MainActivity::class.java)
                startActivity(mIntent)
            }
            R.id.ic_search -> run {
                val mIntent = Intent(this, SearchActivity::class.java)
                startActivity(mIntent)
            }
        }
    }


    private fun initUI() {
        btnCamera.setOnClickListener {
            captureImageUsingCamera()
        }

        btnGallery.setOnClickListener {
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
        ImagePicker.with(this).crop().galleryOnly().start(REQUEST_FROM_GALLERY)
    }

    private fun captureImageUsingCamera() {
        ImagePicker.with(this).crop().cameraOnly().start(REQUEST_FROM_CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_FROM_CAMERA) {
            when (requestCode) {
                REQUEST_FROM_CAMERA -> {
                    imgProfile.setImageURI(data!!.data)
                    FirebaseStorageManager().uploadImage(this, data.data!!)
                }
                REQUEST_FROM_GALLERY -> {
                    imgProfile.setImageURI(data!!.data)
                    FirebaseStorageManager().uploadImage(this, data.data!!)
                }
            }
        }
    }
}