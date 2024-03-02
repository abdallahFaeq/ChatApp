package com.example.chatapp.model.remote.webservices

import com.example.chatapp.model.remote.FireStoreManager
import com.example.chatapp.model.remote.data.Message
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class MessageService {
    companion object{
        fun sendMessage(message:Message,onSuccessListener: OnSuccessListener<Void>,onFailureListener: OnFailureListener){
            var document =FireStoreManager
                .getFireStoreInstance()
                .collection("Rooms")
                .document(message.roomId?:"")

            var messageDocument = document.collection("Messages")
                .document()
                message.id = messageDocument.id
            messageDocument.set(message)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener)
        }

        private fun getMessagesCollection(roomId:String):CollectionReference{
            return FireStoreManager
                .getFireStoreInstance()
                .collection("Rooms")
                .document(roomId)
                .collection("Messages")
        }
        fun retrieveMessages(roomId:String,listener:EventListener<QuerySnapshot>){
            var messagesCollection = getMessagesCollection(roomId)

            messagesCollection
                .orderBy("time",Query.Direction.ASCENDING)
                .addSnapshotListener(listener)
        }
    }
}