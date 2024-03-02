package com.example.chatapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivityLoginBinding
import com.example.chatapp.ui.home.HomeActivity
import com.example.chatapp.ui.register.RegisterActivity

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(),Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.loginVM = viewModel
        viewModel.navigator = this

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initializeViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun goToRegisterActivity() {
        startActivity(Intent(this,RegisterActivity::class.java))
    }

    override fun goToHomeActivity() {
        startActivity(Intent(this,HomeActivity::class.java))
    }
}