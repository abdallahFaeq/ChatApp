package com.example.chatapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<N>:ViewModel() {
    var navigator:N?=null

    var toastMessage = MutableLiveData<String>()
    val toastMessageLiveData :LiveData<String> = toastMessage


}