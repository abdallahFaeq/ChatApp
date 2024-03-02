package com.example.chatapp.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.model.remote.data.Room
import com.example.chatapp.model.remote.webservices.RoomService

class HomeViewModel:BaseViewModel<Navigator>() {
    private var roomsMutableLiveData = MutableLiveData<List<Room>>()
    var roomsListLiveData = roomsMutableLiveData
    fun addRoom(){
        navigator?.goToAddRoomActivity()
    }

    fun getRoomsList(){
        var roomsList = mutableListOf<Room>()
        RoomService
            .getAllRooms( {
                var result = it.toObjects(Room::class.java)
                for (document in result){
                    roomsList.add(document)
                }
                roomsMutableLiveData.postValue(roomsList)
            },  {
                toastMessage.postValue(it.localizedMessage)
            })
    }
}