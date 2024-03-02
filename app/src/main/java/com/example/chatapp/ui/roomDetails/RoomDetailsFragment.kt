package com.example.chatapp.ui.roomDetails

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.databinding.ActivityRoomDetailsBinding
import com.example.chatapp.model.remote.data.Room

class RoomDetailsFragment:BaseFragment<ActivityRoomDetailsBinding,RoomDetailsViewModel>() {

    var room: Room?=null
    lateinit var adapter:MessagesAdapter
    val args : RoomDetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingFragment.vm = viewModelFragment

//        // recieve intent
//        if (intent.hasExtra("room")){
//            room = intent.getParcelableExtra("room")
//        }
        //todo:recieve data by safe args
        room = args.room

        viewModelFragment.roomId = room?.id
        viewModelFragment.retrieveMessagesLive()

        initRecyclerView()
        observeToMessagesLive()
    }

    private fun observeToMessagesLive() {
        viewModelFragment
            .messages
            .observe(viewLifecycleOwner){
                adapter.changeData(it)
                bindingFragment.roomDetailsRecyclerView.smoothScrollToPosition(adapter.messages.size)
            }
    }

    private fun initRecyclerView() {
        adapter = MessagesAdapter(mutableListOf())
        var layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        layoutManager.stackFromEnd = true
        bindingFragment.roomDetailsRecyclerView.layoutManager = layoutManager
        bindingFragment.roomDetailsRecyclerView.adapter = adapter
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_room_details
    }

    override fun initializeViewModel(): RoomDetailsViewModel {
        return ViewModelProvider(this).get(RoomDetailsViewModel::class.java)
    }
}