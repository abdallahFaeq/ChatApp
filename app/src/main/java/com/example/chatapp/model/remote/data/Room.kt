package com.example.chatapp.model.remote.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room (
    var id:String?="",
    var name:String?="",
    var desc:String?=""
):Parcelable