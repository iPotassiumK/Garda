package com.example.garda.detailblog

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BlogEntity (
    var nameTitle:String = "",
    var nameFull:String = "",
    var nameDesc:String = "",
    var imageBlog:Int = 0
): Parcelable