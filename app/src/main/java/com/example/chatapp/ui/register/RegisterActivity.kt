package com.example.chatapp.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivityRegisterBinding
import com.example.chatapp.ui.home.HomeActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding,RegisterViewModel>(),Navigator{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        viewModel.navigator = this
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initializeViewModel(): RegisterViewModel {
        return ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun goToHomeActivity() {
        startActivity(Intent(this,HomeActivity::class.java))
    }

}