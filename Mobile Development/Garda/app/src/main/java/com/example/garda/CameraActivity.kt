package com.example.garda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.example.tflite.view.ImageClassifierActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.ml.modeldownloader.CustomModel
import com.google.firebase.ml.modeldownloader.CustomModelDownloadConditions
import com.google.firebase.ml.modeldownloader.DownloadType
import com.google.firebase.ml.modeldownloader.FirebaseModelDownloader
import kotlinx.android.synthetic.main.activity_camera.*
import org.tensorflow.lite.Interpreter


class CameraActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var interpreter: Interpreter
    private lateinit var ic_home : ImageButton
    private lateinit var ic_search : ImageButton
    private lateinit var imageclassifieractivity : ImageClassifierActivity

    companion object {
        const val REQUEST_FROM_CAMERA = 1001;
        const val REQUEST_FROM_GALLERY = 1002;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

//        val conditions = CustomModelDownloadConditions.Builder()
//            .requireWifi()  // Also possible: .requireCharging() and .requireDeviceIdle()
//            .build()
//        FirebaseModelDownloader.getInstance()
//            .getModel("your_model", DownloadType.LOCAL_MODEL_UPDATE_IN_BACKGROUND,
//                conditions)
//            .addOnSuccessListener { model: CustomModel? ->
//                // Download complete. Depending on your app, you could enable the ML
//                // feature, or switch from the local model to the remote model, etc.
//
//                // The CustomModel object contains the local path of the model file,
//                // which you can use to instantiate a TensorFlow Lite interpreter.
//                val modelFile = model?.file
//                if (modelFile != null) {
//                    interpreter = Interpreter(modelFile)
//                }
//            }

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