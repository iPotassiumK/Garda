package com.example.garda.detailblog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.garda.R

class DetailBlogActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_BLOG = "extra_blog"
    }
    val blogs
        get() = intent.extras?.getParcelable<BlogEntity>(EXTRA_BLOG)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_blog)

        var nameTitle:TextView = findViewById(R.id.txt_name_title)
        var nameFull:TextView = findViewById(R.id.txt_name_full)
        var imageBlog: ImageView = findViewById(R.id.imageBlog)

        nameTitle.text = blogs?.nameTitle
        nameFull.text = blogs?.nameFull
        blogs?.imageBlog?.let { imageBlog.setImageResource(it) }
    }
}