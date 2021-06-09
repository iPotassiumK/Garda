package com.example.garda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.example.garda.detail.AboutActivity
import com.example.garda.detail.HarvestActivity
import com.example.garda.detail.InstructionActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso


class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btn_about : Button
    private lateinit var btn_instruction : Button
    private lateinit var btn_harvest : Button
    private lateinit var txt_names: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        btn_about = findViewById(R.id.btn_about)
        btn_about.setOnClickListener(this)

        btn_harvest = findViewById(R.id.btn_harvest)
        btn_harvest.setOnClickListener(this)

        btn_instruction = findViewById(R.id.btn_instruction)
        btn_instruction.setOnClickListener(this)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        val db = FirebaseFirestore.getInstance()
        val intent = intent
        val resultPlant = intent.getStringExtra("resultPlant")
        val model_name = intent.getStringExtra("model_name")
        val name = intent.getStringExtra("name")

        val txt_first:TextView = findViewById(R.id.txt_name)
        val txt_second:TextView = findViewById(R.id.txt_science)
        val imgPlant: ImageView = findViewById(R.id.imgPlants)



        if (resultPlant != null) {
            txt_names = resultPlant

        } else{
            if (model_name != null) {
                txt_names = model_name
            }
        }

        val docRef = db.collection("plants").document("${txt_names}")

        docRef.get()
            .addOnSuccessListener{ document ->
                if(document != null) {

                    Log.d("exist", "DocumentSnapshot data: ${document.data}")

                    txt_first.text = document.getString("name")

                } else {
                    Log.d("sorry there is no exist", "No Such Document")
                }
            }
            .addOnFailureListener {exception ->
                Log.d("sorry you get an error", "get failed with", exception)
            }

        val docRef2 = db.collection("plants").document("${txt_names}")

        docRef2.get()
            .addOnSuccessListener{ document ->
                if(document != null) {

                    Log.d("exist", "DocumentSnapshot data: ${document.data}")

                    txt_second.text = document.getString("science")

                } else {
                    Log.d("sorry there is no exist", "No Such Document")
                }
            }
            .addOnFailureListener {exception ->
                Log.d("sorry you get an error", "get failed with", exception)
            }

        val docRef3 = db.collection("plants").document("${txt_names}")

        docRef3.get()
            .addOnSuccessListener{ document ->
                if(document != null) {

                    Log.d("exist", "DocumentSnapshot data: ${document.data}")

                    var link = document.getString("imgPlants")
                    Picasso.get().load(link).into(imgPlant)

                } else {
                    Log.d("sorry there is no exist", "No Such Document")
                }
            }
            .addOnFailureListener {exception ->
                Log.d("sorry you get an error", "get failed with", exception)
            }
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_about -> run {
                val mIntent = Intent(this, AboutActivity::class.java)
                val txt_names = txt_names
                mIntent.putExtra("txt_names", txt_names)
                startActivity(mIntent)
            }
            R.id.btn_harvest -> run {
                val mIntent = Intent(this, HarvestActivity::class.java)
                val txt_names = txt_names
                mIntent.putExtra("txt_names", txt_names)
                startActivity(mIntent)
            }
            R.id.btn_instruction -> run {
                val mIntent = Intent(this, InstructionActivity::class.java)
                val txt_names = txt_names
                mIntent.putExtra("txt_names", txt_names)
                startActivity(mIntent)
            }
        }
    }
}