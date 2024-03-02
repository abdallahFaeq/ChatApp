package com.example.chatapp.ui.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.databinding.ActivityLoginBinding

class LoginFragment:BaseFragment<ActivityLoginBinding,LoginViewModel>(),Navigator {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingFragment.loginVM = viewModelFragment
        viewModelFragment.navigator = this
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initializeViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun goToRegisterActivity() {
        //todo:go to register fragment
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    override fun goToHomeActivity() {
        // todo:go to home fragment
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }
}