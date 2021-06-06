package com.example.garda

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.example.garda.listsearch.SearchActivity
import com.example.tflite.plants.Classifier
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_camera.*


class CameraActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var ic_home : ImageButton
    private lateinit var ic_search : ImageButton
    private val mInputSize = 150
    private val mModelPath = "Plants.tflite"
    private val mLabelPath = "label.txt"
    private lateinit var classifier: Classifier
    private var condition = false

    companion object {
        const val REQUEST_FROM_CAMERA = 1001;
        const val REQUEST_FROM_GALLERY = 1002;

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        initClassifier()

        ic_home = findViewById(R.id.ic_home)
        ic_home.setOnClickListener(this)

        ic_search = findViewById(R.id.ic_search)
        ic_search.setOnClickListener(this)

        initUI()
    }

    private fun initClassifier() {
        classifier = Classifier(assets, mModelPath, mLabelPath, mInputSize)
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
        btnPredict.setOnClickListener {
            if (condition) {
               findViewById<ImageView>(R.id.imgPlants)
                val bitmap = ((imgPlants as ImageView).drawable as BitmapDrawable).bitmap
                val result = classifier.recognizeImage(bitmap)
                val resultPlant = result.get(0).title


                runOnUiThread {
                    Toast.makeText(this, resultPlant, Toast.LENGTH_SHORT).show()

                    val moveWithDataIntent = Intent(this, DetailActivity::class.java)
                    moveWithDataIntent.putExtra(DetailActivity.EXTRA_PLANT, resultPlant)
                    startActivity(moveWithDataIntent)
                }
           } else {
                Toast.makeText(this, "Please, choose an image!", Toast.LENGTH_SHORT).show()
            }
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
        if (requestCode == REQUEST_FROM_CAMERA || requestCode == REQUEST_FROM_GALLERY) {
            when (requestCode) {

                REQUEST_FROM_CAMERA -> {
                    imgPlants.setImageURI(data!!.data)
                    FirebaseStorageManager().uploadImage(this, data.data!!)
                    condition = true
                }

                REQUEST_FROM_GALLERY -> {
                    imgPlants.setImageURI(data!!.data)
                    FirebaseStorageManager().uploadImage(this, data.data!!)
                    condition = true
                }
            }
        }
    }


}