package com.example.garda.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.garda.R
import com.example.garda.SliderAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment : Fragment() {

    private var currentPage = 0
    private var numPages = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

        val lists: List<String> = listOf(
            "https://i.pinimg.com/originals/70/96/78/709678cc727b37324815304ba0a4d340.jpg",
            "https://www.brainyquote.com/photos_tr/en/a/anthonyjdangelo/153989/anthonyjdangelo1-2x.jpg",
            "https://cdn.lurn.com/blog/files/uploads/image_32.png"
        )

        val assets = listOf(
            R.drawable.foto1,
            R.drawable.foto2,
            R.drawable.foto3
        )


        createSlider(assets)
    }

    private fun createSlider(string: List<Int>) {

        slider.adapter = context?.let { SliderAdapter(it, string) }
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