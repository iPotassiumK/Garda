package com.example.garda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.viewpager2.widget.ViewPager2
import com.example.garda.detailfragment.AboutFragment
import com.example.garda.listsearch.PlantsEntity
import com.example.garda.viewpager.SectionPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {

        const val EXTRA_PLANT = "extra_plant"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_1,
            R.string.tab_2,
            R.string.tab_3,
            R.string.tab_4
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val myFragment = AboutFragment()

        val bundle = Bundle()
        bundle.putString("message", txt_name.text.toString())
        myFragment.arguments = bundle

        val sectionsPagerAdapter = SectionPagerAdapter(this, bundle)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

        //passdatafromsearch
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)


        val db = FirebaseFirestore.getInstance()
        val intent = intent
        val resultPlant = intent.getStringExtra("resultPlant")
        val model_name = intent.getStringExtra("model_name")

        val txt_first:TextView = findViewById(R.id.txt_name)
        val txt_second:TextView = findViewById(R.id.txt_science)
        val imgPlant: ImageView = findViewById(R.id.imgPlants)
        var txt_names: String?


        if (resultPlant != null) {
            txt_names = resultPlant

        } else{
            txt_names = model_name
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

}