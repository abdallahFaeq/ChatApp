package com.example.chatapp.ui.register

import androidx.databinding.ObservableField
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.model.remote.webservices.FireStoreService
import com.example.chatapp.model.remote.data.User
import com.example.chatapp.utils.Data
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegisterViewModel : BaseViewModel<Navigator>(){
    // hold data
    // handle logic

    var name = ObservableField<String>()
    var email = ObservableField<String>()
    var password = ObservableField<String>()

    var nameError = ObservableField<Boolean>()
    var emailError = ObservableField<Boolean>()
    var passError = ObservableField<Boolean>()

    var mAuth : FirebaseAuth? =null

    fun register(){
        // validate input
        if (validate()){
            // call api
            // register inputs into your storage
            // create user with email and password
            initializeAuth()
            createUserWithEmailAndPassword()
        }
    }

    private fun initializeAuth() {
        if (mAuth == null){
            mAuth = Firebase.auth
        }
    }

    private fun createUserWithEmailAndPassword() {
        mAuth?.let{
            it.createUserWithEmailAndPassword(
                email.get()?:"",
                password.get()?:""
            )
                .addOnCompleteListener({
                    if (it.isSuccessful){
                        // toast successfully
                        toastMessage.postValue("register successfully")
                        // save data on cloun FireStore
                        setUser(User(
                            mAuth?.currentUser?.uid?:"",
                            name.get()?:"",
                            email.get()?:""
                        ))
                        // navigate to home activity
                        navigator?.goToHomeActivity()
                    }else{
                        // show toast message that contain error
                        toastMessage.postValue(it.exception?.localizedMessage)
                    }
                })
        }
    }

    fun setUser(user:User){
        FireStoreService
            .addUser(user){
                if (it.isSuccessful){
                    toastMessage.postValue("save data successfully")
                    Data.user = user
                }else{
                    toastMessage.postValue(it.exception?.localizedMessage)
                }
            }
    }

    private fun validate(): Boolean {
        var isValid = true
        if (name.get().isNullOrBlank()){
            nameError.set(true)
            isValid = false
        }else{
            nameError.set(false)
            isValid = true
        }
        if (email.get().isNullOrBlank()){
            emailError.set(true)
            isValid = false
        }else{
            emailError.set(false)
            isValid = true
        }
        if (password.get().isNullOrBlank() || password.get()?.length?:0 < 6 ){
            passError.set(true)
            isValid = false
        }else{
            passError.set(false)
            isValid = true
        }
        return isValid
    }


}