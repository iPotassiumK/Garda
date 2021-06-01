package com.example.garda.listsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.garda.CameraActivity
import com.example.garda.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_plants.view.*

class SearchActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var ic_home: ImageButton
    private lateinit var ic_camera: ImageButton
    lateinit var mSearchText: EditText
    lateinit var mRecyclerView: RecyclerView
    lateinit var mDatabase: DatabaseReference
    lateinit var FirebaseRecyclerAdapter: FirebaseRecyclerAdapter<PlantsEntity, PlantsViewHolder>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        mSearchText = findViewById(R.id.srcView)
        mRecyclerView = findViewById(R.id.rvPlants)
        mDatabase = FirebaseDatabase.getInstance().getReference("plants")

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))



        mSearchText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val searchText = mSearchText.getText().toString()
                loadFirebaseData(searchText)
            }
        })



        ic_home = findViewById(R.id.ic_home)
        ic_home.setOnClickListener(this)

        ic_camera = findViewById(R.id.ic_camera)
        ic_camera.setOnClickListener(this)

    }

    private fun loadFirebaseData(searchText : String) {

        if (searchText.isEmpty()) {
            FirebaseRecyclerAdapter.cleanup()
            mRecyclerView.adapter = FirebaseRecyclerAdapter

        } else {

            val firebaseSearchQuery = mDatabase.orderByChild("name").startAt(searchText).endAt(searchText+"\\uf8ff")

            FirebaseRecyclerAdapter =
                object : FirebaseRecyclerAdapter<PlantsEntity, PlantsViewHolder>(
                    PlantsEntity::class.java,
                    R.layout.list_item_plants,
                    PlantsViewHolder::class.java,
                    firebaseSearchQuery
                ) {
                    override fun populateViewHolder(
                        viewHolder: PlantsViewHolder?,
                        model: PlantsEntity?,
                        position: Int
                    ) {
                        viewHolder?.mView?.plantsName?.setText(model?.name)
                        viewHolder?.mView?.plantsScience?.setText(model?.science)
                        Picasso.with(applicationContext).load(model?.imgPlants)
                            .into(viewHolder?.mView?.plantsImage)
                    }
                }
            mRecyclerView.adapter = FirebaseRecyclerAdapter
        }
    }

    class PlantsViewHolder(var mView : View) : RecyclerView.ViewHolder(mView){

    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ic_camera -> run {
                val mIntent = Intent(this, CameraActivity::class.java)
                startActivity(mIntent)
            }
            R.id.ic_camera -> run {
                val mIntent = Intent(this, CameraActivity::class.java)
                startActivity(mIntent)
            }
        }
    }
}
