package com.example.garda.detailfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.garda.R
import com.google.firebase.firestore.FirebaseFirestore

/**
 * A simple [Fragment] subclass.
 */

class JournalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_journal, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        //get data from firebasefirestore
//        val db = FirebaseFirestore.getInstance()
//
//        val txt_journal:TextView = view.findViewById(R.id.txt_journal)
////        val txt_name: TextView = view.findViewById(R.id.txt_name)
//
//        val docRef = db.collection("plants").document("chili")
//        docRef.get()
//            .addOnSuccessListener{ document ->
//                if(document != null) {
//
//                    Log.d("exist", "DocumentSnapshot data: ${document.data}")
//
//                    txt_journal.text = document.getString("journal")
//
//                } else {
//                    Log.d("sorry there is no exist", "No Such Document")
//                }
//            }
//            .addOnFailureListener {exception ->
//                Log.d("sorry you get an error", "get failed with", exception)
//            }
    }
}
