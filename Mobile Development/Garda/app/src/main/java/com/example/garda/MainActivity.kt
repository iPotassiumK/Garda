package com.example.garda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.garda.fragment.CameraFragment
import com.example.garda.fragment.HomeFragment
import com.example.garda.fragment.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val cameraFragment = CameraFragment()
    private val searchFragment = SearchFragment()
    private var currentPage = 0
    private var numPages = 0

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lists: List<String> = listOf(
            "https://i.pinimg.com/originals/70/96/78/709678cc727b37324815304ba0a4d340.jpg",
            "https://www.brainyquote.com/photos_tr/en/a/anthonyjdangelo/153989/anthonyjdangelo1-2x.jpg",
            "https://cdn.lurn.com/blog/files/uploads/image_32.png",
        )


        val assets = listOf(
            R.drawable.foto1,
            R.drawable.foto2,
            R.drawable.foto3
        )


        createSlider(assets)

        makeCurrentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_camera -> makeCurrentFragment(cameraFragment)
                R.id.ic_search -> makeCurrentFragment(searchFragment)
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_wrapper, fragment)
        transaction.commit()
    }

    private fun createSlider(string: List<Int>) {

        slider.adapter = SliderAdapter(this, string)
        indicator.setViewPager(slider)
        val density = resources.displayMetrics.density
        //Set circle indicator radius
        indicator.radius = 5 * density
        numPages = string.size


        val update = Runnable {
            if (currentPage === numPages) {
                currentPage = 0
            }
            slider.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post(update)
            }
        }, 5000, 5000)
        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(pos: Int) {}
        })

    }
}