package com.example.garda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.garda.detailblog.BlogAdapter
import com.example.garda.detailblog.BlogEntity
import com.example.garda.detailblog.DataDummyBlog
import com.example.garda.listsearch.SearchActivity
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var rvBlog: RecyclerView
    private lateinit var ic_camera : ImageButton
    private lateinit var ic_search :ImageButton
    private var blogs: ArrayList<BlogEntity> = arrayListOf()

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


}