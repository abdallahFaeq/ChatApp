package com.example.chatapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivityHomeBinding
import com.example.chatapp.model.remote.data.Room
import com.example.chatapp.ui.add_room.AddRoomActivity
import com.example.chatapp.ui.roomDetails.RoomDetailsActivity

class HomeActivity : BaseActivity<ActivityHomeBinding,HomeViewModel>(),Navigator {
    lateinit var adapter :HomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        viewModel.navigator = this
        viewModel.getRoomsList()

        initRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        observeToLiveData()
        adapter.onItemClickListener = object :HomeAdapter.OnItemClickListener{
            override fun onItemClick(room: Room) {
                var intent = Intent(this@HomeActivity,RoomDetailsActivity::class.java)
                intent.putExtra("room",room)
                startActivity(intent)
            }
        }
    }

    private fun observeToLiveData() {
        viewModel.roomsListLiveData.observe(this){
            adapter.changeData(it)
        }
    }

    private fun initRecyclerView() {
        adapter = HomeAdapter(listOf())
        binding.homeRecyclerView.adapter = adapter
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initializeViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun goToAddRoomActivity() {
        startActivity(Intent(this,AddRoomActivity::class.java))
    }

}