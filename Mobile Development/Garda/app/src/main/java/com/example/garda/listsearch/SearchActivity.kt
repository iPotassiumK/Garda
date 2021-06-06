package com.example.garda.listsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.garda.CameraActivity
import com.example.garda.MainActivity
import com.example.garda.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_plants.view.*

class SearchActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var ic_home: ImageButton
    private lateinit var ic_camera: ImageButton
    lateinit var mSearchText : EditText
    lateinit var mRecyclerView : RecyclerView
    lateinit var mDatabase : DatabaseReference

    lateinit var FirebaseRecyclerAdapter : FirebaseRecyclerAdapter<PlantsEntity , PlantsViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        mSearchText =findViewById(R.id.srcView)
        mRecyclerView = findViewById(R.id.rvPlants)

        ic_home = findViewById(R.id.ic_home)
        ic_home.setOnClickListener(this)

        ic_camera = findViewById(R.id.ic_camera)
        ic_camera.setOnClickListener(this)

        mDatabase = FirebaseDatabase.getInstance().getReference("PlantsEntity")

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))

        mSearchText.addTextChangedListener(object  : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {

                val searchText = mSearchText.getText().toString().trim()

                loadFirebaseData(searchText)
            }
        } )
    }

    private fun loadFirebaseData(searchText : String) {

        if(searchText.isEmpty()){

            FirebaseRecyclerAdapter.cleanup()
            mRecyclerView.adapter = FirebaseRecyclerAdapter

        }else {

            val firebaseSearchQuery = mDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff")

            FirebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<PlantsEntity,PlantsViewHolder>(

                PlantsEntity::class.java,
                R.layout.list_item_plants,
                PlantsViewHolder::class.java,
                firebaseSearchQuery

            ) {
                override fun populateViewHolder(viewHolder: PlantsViewHolder, model: PlantsEntity, position: Int) {

                    viewHolder.mview.plantsName.setText(model.name)
                    viewHolder.mview.plantsScience.setText(model.science)
                    Picasso.get().load(model.imgPlants).into(viewHolder.mview.plantsImage)
                }
            }
            mRecyclerView.adapter = FirebaseRecyclerAdapter
        }
    }

    // // View Holder Class
    class PlantsViewHolder(var mview : View) : RecyclerView.ViewHolder(mview) {

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
        }
    }
}