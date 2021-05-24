package com.example.garda.fragment

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage

class FirebaseStorageManager {
    private val TAG = "FirebaseStorageManager "
    private val mStorageRef = FirebaseStorage.getInstance().reference
    private lateinit var mProgressDialog: ProgressDialog

    fun uploadImage (mContext: Context, imageURI: Uri) {
        mProgressDialog = ProgressDialog(mContext)
        mProgressDialog.setMessage("Please wait, image being uploading...")
       val uploadTask = mStorageRef.child("users/profilePic.png").putFile(imageURI)

        uploadTask.addOnSuccessListener {
            //success
            Log.e(TAG,"image successfully")
        }.addOnFailureListener {
            Log.e(TAG,"image upload failed ${it.printStackTrace()}")
        }
    }
}