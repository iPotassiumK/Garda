package com.example.garda.detailfragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.garda.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_detail.*

class AboutFragment : Fragment() {
//    lateinit var dataPasser: OnDataPass

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        dataPasser = context as OnDataPass
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

//    fun passData(txt_name: String){
//        dataPasser.onDataPass(txt_name)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get data from firebasefirestore
        val db = FirebaseFirestore.getInstance()

        val txt_about:TextView = view.findViewById(R.id.txt_about)
        var txt_names = ""
//        val txt_names = dataPasser.onDataPass(txt_name.toString())

        val docRef = db.collection("plants").document("${txt_names}")

        docRef.get()
            .addOnSuccessListener{ document ->
                if(document != null) {

                    Log.d("exist", "DocumentSnapshot data: ${document.data}")

                    txt_about.text = document.getString("about")

                } else {
                    Log.d("sorry there is no exist", "No Such Document")
                }
            }
            .addOnFailureListener {exception ->
                Log.d("sorry you get an error", "get failed with", exception)
            }
    }
}
