package com.example.garda.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.garda.MainActivity
import com.example.garda.R
import com.google.android.material.tabs.TabLayout

class OnBoardingActivity : AppCompatActivity() {

    var OnBoardingViewPagerAdapter : OnBoardingViewPagerAdapter? = null
    var tabLayout: TabLayout? = null
    var OnBoardingViewPager: ViewPager? = null
    var next: TextView? = null
    var position = 0
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        if (restorePrefData()){
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
            finish()
        }

        tabLayout = findViewById(R.id.tab_indicator)
        next = findViewById(R.id.next)

       val onBoardingData : MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(OnBoardingData("GARDA is a gardening assistant that can help you in sorting plants information with help of machine learning","", R.drawable.gambar1))
        onBoardingData.add(OnBoardingData("find your favorite plants in here","", R.drawable.gambar2))
        onBoardingData.add(OnBoardingData("you can predict your plant type","", R.drawable.gambar3))
        onBoardingData.add(OnBoardingData("enjoy with GARDA fun features","", R.drawable.gambar4))

        setOnBoardingViewPagerAdapter(onBoardingData)

        position = OnBoardingViewPager!!.currentItem

        next?.setOnClickListener{
            if(position < onBoardingData.size){
                position++
                OnBoardingViewPager!!.currentItem = position
            }
            if (position == onBoardingData.size){
                savePrefData()
                val i = Intent(applicationContext, MainActivity::class.java)
                startActivity(i)
            }
        }

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?){

            }

            override fun onTabUnselected(tab: TabLayout.Tab?){

            }

            override fun onTabSelected(tab: TabLayout.Tab?){
                position = tab!!.position
                if(tab.position == onBoardingData.size -1){
                    next!!.text = "Get Started"}
                else{
                    next!!.text= "Next"
                }
                }
            })
    }

    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>){

        OnBoardingViewPager = findViewById(R.id.screenPager);
        OnBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingData)
        OnBoardingViewPager!!.adapter = OnBoardingViewPagerAdapter
        tabLayout?.setupWithViewPager(OnBoardingViewPager)
        

    }

    private fun savePrefData(){
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.putBoolean("isFirstTimeRun", true)
        editor.apply()
    }

    private fun restorePrefData(): Boolean{
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return sharedPreferences!!.getBoolean("isFirstTimeRun", false)
    }
}