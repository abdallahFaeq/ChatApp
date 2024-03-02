package com.example.chatapp.model.remote

import com.google.firebase.firestore.FirebaseFirestore

object FireStoreManager {
    private var instance:FirebaseFirestore? =null

    fun getFireStoreInstance():FirebaseFirestore{
        if (instance == null){
            instance = FirebaseFirestore.getInstance()
        }
        return instance!!
    }
}