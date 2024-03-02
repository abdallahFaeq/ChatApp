package com.example.chatapp.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<DB:ViewDataBinding,VM:BaseViewModel<*>>:AppCompatActivity() {
    lateinit var binding:DB
    lateinit var viewModel:VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,getLayoutId())
        viewModel = initializeViewModel()
        viewModel.toastMessageLiveData.observe(this){
            Toast.makeText(
                this@BaseActivity,
                it,
                Toast.LENGTH_SHORT
                ).show()
        }
    }
    abstract fun getLayoutId():Int
    abstract fun initializeViewModel():VM

}