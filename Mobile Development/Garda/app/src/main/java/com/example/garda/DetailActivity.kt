package com.example.garda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.viewpager2.widget.ViewPager2
import com.example.garda.listsearch.SearchActivity
import com.example.garda.viewpager.SectionPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity()
{

    companion object {
        const val EXTRA_PLANT = "extra_plant"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_1,
            R.string.tab_2,
            R.string.tab_3
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

//        val db = FirebaseFirestore.getInstance()
//
//        val txt_science: TextView = findViewById(R.id.txt_science)
//        val txt_name: TextView = findViewById(R.id.txt_name)
//
//        val docRef = db.collection("plants").document("broccoli")
//        docRef.get()
//            .addOnSuccessListener{ document ->
//                if(document != null) {
//
//                    Log.d("exist", "DocumentSnapshot data: ${document.data}")
//
//                    txt_science.text = document.getString("science")
//
//                } else {
//                    Log.d("sorry there is no exist", "No Such Document")
//                }
//            }
//            .addOnFailureListener {exception ->
//                Log.d("sorry you get an error", "get failed with", exception)
//            }



        val bundle = Bundle()
        val sectionsPagerAdapter = SectionPagerAdapter(this, bundle)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

        //passdatafromsearch
        val actionBar : ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

//        val intent = Intent()
//        var resultPlant = intent.getStringExtra("txt_name")
//        txt_name.setText(resultPlant)
//        var txt_science = intent.getStringExtra("txt_science")
//        var imgPlants = intent.getIntExtra("imgPlants", 0)

    }
}