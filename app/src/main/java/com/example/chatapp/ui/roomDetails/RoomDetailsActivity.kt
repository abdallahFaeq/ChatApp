package com.example.chatapp.ui.roomDetails

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivityRoomDetailsBinding
import com.example.chatapp.model.remote.data.Room

class RoomDetailsActivity : BaseActivity<ActivityRoomDetailsBinding,RoomDetailsViewModel>() {
    var room:Room?=null
    lateinit var adapter:MessagesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        // accept intent
        if (intent.hasExtra("room")){
            room = intent.getParcelableExtra("room")
        }

        viewModel.roomId = room?.id
        viewModel.retrieveMessagesLive()

        initRecyclerView()
        observeToMessagesLive()

    }

    private fun observeToMessagesLive() {
        viewModel
            .messages
            .observe(this){
                adapter.changeData(it)
                binding.roomDetailsRecyclerView.smoothScrollToPosition(adapter.messages.size)
            }
    }

    private fun initRecyclerView() {
        adapter = MessagesAdapter(mutableListOf())
        var layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        layoutManager.stackFromEnd = true
        binding.roomDetailsRecyclerView.layoutManager = layoutManager
        binding.roomDetailsRecyclerView.adapter = adapter
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_room_details
    }

    override fun initializeViewModel(): RoomDetailsViewModel {
        return ViewModelProvider(this).get(RoomDetailsViewModel::class.java)
    }
}