package com.example.chatapp.ui.register

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.databinding.ActivityRegisterBinding

class RegisterFragment:BaseFragment<ActivityRegisterBinding,RegisterViewModel>(),Navigator {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingFragment.vm = viewModelFragment
        viewModelFragment.navigator = this
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initializeViewModel(): RegisterViewModel {
        return ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun goToHomeActivity() {
        //todo:go to home fragment
        findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
    }
}