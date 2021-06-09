package com.example.garda.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.garda.R
import com.google.firebase.firestore.FirebaseFirestore

class InstructionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)

        val db = FirebaseFirestore.getInstance()

        val txt_instruction: TextView = findViewById(R.id.txt_instruction)
        val txt_names= intent.getStringExtra("txt_names")


        val docRef = db.collection("plants").document("${txt_names}")

        docRef.get()
            .addOnSuccessListener{ document ->
                if(document != null) {

                    Log.d("exist", "DocumentSnapshot data: ${document.data}")

                    txt_instruction.text = document.getString("instruction")

                } else {
                    Log.d("sorry there is no exist", "No Such Document")
                }
            }
            .addOnFailureListener {exception ->
                Log.d("sorry you get an error", "get failed with", exception)
            }
    }
}