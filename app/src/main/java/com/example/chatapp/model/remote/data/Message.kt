package com.example.chatapp.model.remote.data

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat

data class Message(
    var id:String?=null,
    var messageText:String?=null,
    var senderId:String?=null,
    var senderName:String?=null,
    var roomId:String?=null,
    var time:Timestamp?=null
){
    fun formatTime():String{
        var formatter = SimpleDateFormat("dd:MMM ... hh:MM:ss")
        return formatter.format(time?.toDate())
    }
}

