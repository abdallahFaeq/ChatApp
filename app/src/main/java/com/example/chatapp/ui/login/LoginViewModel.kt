package com.example.chatapp.ui.login

import androidx.databinding.ObservableField
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.model.remote.data.User
import com.example.chatapp.model.remote.webservices.FireStoreService
import com.example.chatapp.utils.Data
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginViewModel : BaseViewModel<Navigator>() {
    var email = ObservableField<String>("Abdallah@gmail.com")
    var password = ObservableField<String>("123456")

    var emailError = ObservableField<Boolean>()
    var passwordError = ObservableField<Boolean>()

    var mAuth:FirebaseAuth? =null

    fun gotoRegisteration(){
        navigator?.goToRegisterActivity()
    }

    fun login(){
        // validate password and email
        // show error if there

        if (validate()){
            // call api
            initializeAuth()
            signInWithEmailAndPassword()
        }
    }

    private fun signInWithEmailAndPassword() {
        mAuth?.signInWithEmailAndPassword(
            email.get()?:"",
            password.get()?:""
        )
            ?.addOnCompleteListener({
                if (it.isSuccessful){
                    toastMessage.postValue("sign in successfully")
                    retrieveDataOfUser(mAuth!!.currentUser?.uid!!)
                    navigator?.goToHomeActivity()
                }else{
                    toastMessage.postValue(it.exception?.localizedMessage)
                }
            })
    }

    private fun retrieveDataOfUser(userId:String) {
        FireStoreService
            .getUser(userId){
                if (it.isSuccessful){
                    toastMessage.postValue("retrieve successfully")
                    Data.user = it.result.toObject(User::class.java)
                    Data.user?.id = userId
                }else{
                    toastMessage.postValue(it.exception?.localizedMessage)
                }
            }
    }

    private fun initializeAuth() {
        if (mAuth == null){
            mAuth = Firebase.auth
        }
    }

    fun validate():Boolean{
        var isValid = false
        if (email.get().isNullOrBlank()){
            // show error
            emailError.set(true)
            isValid = false
        }else{
            // hide error
            emailError.set(false)
            isValid = true
        }
        if (password.get().isNullOrBlank() || password.get()?.length?:0 < 6){
            // show error
            passwordError.set(true)
            isValid = false
        }else{
            // hide error
            passwordError.set(false)
            isValid = true
        }
        return isValid
    }


}