package com.example.chatapp.ui.add_room

import androidx.databinding.ObservableField
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.model.remote.data.Room
import com.example.chatapp.model.remote.webservices.RoomService

class AddRoomViewModel:BaseViewModel<Navigator>() {
    var roomName = ObservableField<String>()
    var roomDesc = ObservableField<String>()

    fun saveRoom(){
        // validate input
        validate()
        // show error if there about binding adapter

        // save room to firestore
        addRoom(Room(
            name = roomName.get(),
            desc = roomDesc.get()
        ))
        // back to homeActivity
        navigator?.backToHomeActivity()
    }

    private fun addRoom(room:Room) {
        RoomService
            .addRoom(room){
                if (it.isSuccessful){
                    toastMessage.postValue("room added successfully")
                }else{
                    toastMessage.postValue(it.exception?.localizedMessage)
                }
            }
    }

    fun validate():Boolean{
       if (roomName.get().isNullOrBlank()
           || roomDesc.get().isNullOrBlank()) return false
        else return true
    }
}