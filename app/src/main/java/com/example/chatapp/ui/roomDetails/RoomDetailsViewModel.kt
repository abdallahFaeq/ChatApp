package com.example.chatapp.ui.roomDetails

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.model.remote.data.Message
import com.example.chatapp.model.remote.webservices.MessageService
import com.example.chatapp.utils.Data
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class RoomDetailsViewModel:BaseViewModel<Navigator>() {
    var messageLive = ObservableField<String>()
    var roomId:String?=null
    var messages = MutableLiveData<MutableList<Message>>()
    fun sendMessage(){
        if (!validate()) return
        // send message to room in fire store
        var message = Message(
            messageText = messageLive.get(),
            senderId = Data.user?.id,
            senderName = Data.user?.name,
            roomId = roomId,
            time = Timestamp.now()
        )

        MessageService
            .sendMessage(message,
                OnSuccessListener {
                    toastMessage.postValue("add messages successful")
                    messageLive.set("")
                },
                OnFailureListener {
                    toastMessage.postValue(it.localizedMessage)
                })
    }

    var messagesList = mutableListOf<Message>()
    fun retrieveMessagesLive(){
        MessageService
            .retrieveMessages(roomId!!,object :EventListener<QuerySnapshot>{
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                 if (error!=null){
                     toastMessage.postValue(error.localizedMessage)
                     return
                 }
                    messagesList.clear()
                    for (dc in value!!.getDocumentChanges()){
                        when(dc.type){
                            DocumentChange.Type.ADDED ->{
                                messagesList.add(dc.document.toObject(Message::class.java))
                            }

                            else -> {}
                        }
                    }
                    messages.value = messagesList
                }
            })
    }

    fun validate():Boolean{
        if (messageLive.get().isNullOrBlank()) return false
        return true
    }
}