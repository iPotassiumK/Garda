package com.example.garda

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.garda.detailfragment.AboutFragment
import com.example.garda.detailfragment.HarvestFragment
import com.example.garda.detailfragment.InstructionFragment
import com.example.garda.listsearch.SearchActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var ic_home : ImageButton
    private lateinit var ic_camera : ImageButton
    private lateinit var ic_search : ImageButton
    private var fragmentabout = AboutFragment()
    private var fragmentinstruction = InstructionFragment()
    private var fragmentharvest = HarvestFragment()

    companion object {
        const val EXTRA_PLANT = "extra_plant"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        replaceFragment(fragmentabout)

//        val intent = intent
//         intent.getStringExtra(resources.getString(R.string.name_plant))

        //get data from firebasefirestore
        val db = FirebaseFirestore.getInstance()

        val fragment_about = findViewById(R.id.fragment_about) as TextView
        val fragment_instruction = findViewById(R.id.fragment_instruction) as TextView
        val fragment_harvest = findViewById(R.id.fragment_harvest) as TextView

        val docRef = db.collection("plants").document(resources.getString(R.string.name_plant))
        docRef.get()
            .addOnSuccessListener{ document ->
                if(document != null) {
                    Log.d("yeayy exist", "DocumentSnapshot data: ${document.data}")

                    fragment_about.text = document.getString("about")
                    fragment_instruction.text = document.getString("instruction")
                    fragment_harvest.text = document.getString("harvest")

                } else {
                    Log.d("sorry there is no exist", "No Such Document")
                }
            }
            .addOnFailureListener {exception ->
                Log.d("sorry you get an error", "get failed with", exception)
            }

        ic_home = findViewById(R.id.ic_home)
        ic_home.setOnClickListener(this)

        ic_camera = findViewById(R.id.ic_camera)
        ic_camera.setOnClickListener(this)

        ic_search = findViewById(R.id.ic_search)
        ic_search.setOnClickListener(this)

    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ic_camera -> run {
                val mIntent = Intent(this, CameraActivity::class.java)
                startActivity(mIntent)
            }
            R.id.ic_home -> run {
                val mIntent = Intent(this, MainActivity::class.java)
                startActivity(mIntent)
            }
            R.id.ic_search -> run {
                val mIntent = Intent(this, SearchActivity::class.java)
                startActivity(mIntent)
            }
            R.id.btn_about -> replaceFragment(fragmentabout)
            R.id.btn_instruction -> replaceFragment(fragmentinstruction)
            R.id.btn_harvest -> replaceFragment(fragmentharvest)
        }
        true
    }

    private fun replaceFragment(fragment : Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
        true
    }
}