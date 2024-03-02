package com.example.chatapp.model.remote.webservices

import com.example.chatapp.model.remote.FireStoreManager
import com.example.chatapp.model.remote.data.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentSnapshot

class FireStoreService {
    companion object{
        fun addUser(user: User,onCompleteListener:OnCompleteListener<Void>){
            FireStoreManager.getFireStoreInstance()
                .collection("Users")
                .document(user.id!!)
                .set(user)
                .addOnCompleteListener(onCompleteListener)
        }

        fun getUser(userId:String,onCompleteListener: OnCompleteListener<DocumentSnapshot>){
            FireStoreManager.getFireStoreInstance()
                .collection("Users")
                .document(userId)
                .get()
                .addOnCompleteListener(onCompleteListener)
        }
    }

}