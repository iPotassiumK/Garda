package com.example.garda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import androidx.recyclerview.widget.RecyclerView
import com.example.garda.detailblog.BlogAdapter
import com.example.garda.detailblog.BlogEntity
import com.example.garda.detailblog.DataDummyBlog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var rvBlog: RecyclerView
    private lateinit var ic_camera : ImageButton
    private lateinit var ic_search :ImageButton
    private var currentPage = 0
    private var numPages = 0
    private var blogs: ArrayList<BlogEntity> = arrayListOf()

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ic_camera = findViewById(R.id.ic_camera)
        ic_camera.setOnClickListener(this)

        ic_search = findViewById(R.id.ic_search)
        ic_search.setOnClickListener(this)

        val assets = listOf(
            R.drawable.foto1,
            R.drawable.foto2,
            R.drawable.foto3
        )


        createSlider(assets)

        rvBlog = findViewById(R.id.rv_main)
        rvBlog.setHasFixedSize(true)

        blogs.addAll(DataDummyBlog.counterBlog)
        showRecylerList()

    }

    private fun showRecylerList() {
        rvBlog.layoutManager = LinearLayoutManager(this)
        val blogAdapter = BlogAdapter(this, blogs)
        rvBlog.adapter = blogAdapter
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ic_camera -> run {
                val mIntent = Intent(this, CameraActivity::class.java)
                startActivity(mIntent)
            }
            R.id.ic_search -> run {
                val mIntent = Intent(this, SearchActivity::class.java)
                startActivity(mIntent)
            }
        }
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