package com.example.chatapp.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:error_visibility")
fun setErrorMessageVisibility(tv:TextView,mess:String?){
    if (mess.isNullOrBlank()){
        // show error
        tv.visibility = View.VISIBLE
    }else{
        // hide error
        tv.visibility = View.GONE
    }
}