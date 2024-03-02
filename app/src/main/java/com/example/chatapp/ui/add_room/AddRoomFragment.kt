package com.example.chatapp.ui.add_room

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.databinding.ActivityAddRoomBinding

class AddRoomFragment:BaseFragment<ActivityAddRoomBinding,AddRoomViewModel>(),Navigator {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingFragment.vm = viewModelFragment
        viewModelFragment.navigator = this
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_add_room
    }

    override fun initializeViewModel(): AddRoomViewModel {
        return ViewModelProvider(this).get(AddRoomViewModel::class.java)
    }

    override fun backToHomeActivity() {
        // finish
        // todo: back to home fragment
        findNavController().popBackStack()
    }
}