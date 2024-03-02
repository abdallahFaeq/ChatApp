package com.example.chatapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<DB:ViewDataBinding,VM:BaseViewModel<*>> :Fragment(){
    lateinit var bindingFragment:DB
    lateinit var viewModelFragment:VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragment = DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            container,
            false
        )
        viewModelFragment = initializeViewModel()

        viewModelFragment.toastMessageLiveData.observe(viewLifecycleOwner){
            Toast.makeText(
                context,
                it,
                Toast.LENGTH_SHORT
            ).show()
        }

        return bindingFragment.root
    }

    abstract fun getLayoutId():Int
    abstract fun initializeViewModel():VM
}