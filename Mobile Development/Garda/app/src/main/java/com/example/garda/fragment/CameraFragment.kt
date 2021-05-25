package com.example.garda.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.garda.R
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_camera.*

class CameraFragment : Fragment() {
    companion object {
        const val REQUEST_FROM_CAMERA = 1001;
        const val REQUEST_FROM_GALLERY = 1002;
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_camera,container, false)

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
            when(requestCode) {
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