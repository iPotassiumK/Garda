package com.example.garda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_FROM_CAMERA = 1001;
        const val REQUEST_FROM_GALLERY = 1002;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        initUI()
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