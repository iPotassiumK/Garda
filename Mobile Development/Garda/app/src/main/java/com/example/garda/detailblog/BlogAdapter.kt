package com.example.garda.detailblog

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.garda.R

class BlogAdapter(val context: Context, private val counterBlog: ArrayList<BlogEntity>): RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val views = LayoutInflater.from(parent.context).inflate(
            R.layout.list_recomendation,
            parent, false)
        return BlogViewHolder(views)
    }

    override fun getItemCount(): Int = counterBlog.size

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val blogs = counterBlog[position]
        holder.nameTitle.text = blogs.nameTitle
        holder.nameDesc.text = blogs.nameDesc
        Glide.with(holder.itemView.context)
            .load(blogs.imageBlog)
            .apply(
                RequestOptions().override(666, 374)
            )
            .into(holder.imageBlog)

        holder.itemView.setOnClickListener {
            val intents = Intent(context, DetailBlogActivity::class.java)
            intents.putExtra(DetailBlogActivity.EXTRA_BLOG, blogs)
            context.startActivity(intents)
        }
    }

    inner class BlogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imageBlog: ImageView = itemView.findViewById(R.id.iv_item_plants)
        var nameTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        var nameDesc: TextView = itemView.findViewById(R.id.tv_item_description)

    }
}