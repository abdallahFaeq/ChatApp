package com.example.chatapp.model.remote.webservices

import com.example.chatapp.model.remote.FireStoreManager
import com.example.chatapp.model.remote.data.Room
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QuerySnapshot

class RoomService {
    companion object{
        fun addRoom(room:Room,onCompleteListener:OnCompleteListener<Void>){
            var document = FireStoreManager
                .getFireStoreInstance()
                .collection("Rooms")
                .document()
            room.id = document.id
            document.set(room)
                .addOnCompleteListener(onCompleteListener)
        }

        fun getAllRooms(onSuccessListener:OnSuccessListener<QuerySnapshot>,onFailureListener:OnFailureListener){
           FireStoreManager
               .getFireStoreInstance()
               .collection("Rooms")
               .get()
               .addOnSuccessListener(onSuccessListener)
               .addOnFailureListener(onFailureListener)
        }
    }
}