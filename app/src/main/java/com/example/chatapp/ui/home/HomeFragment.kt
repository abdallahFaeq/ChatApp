package com.example.chatapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.databinding.ActivityHomeBinding
import com.example.chatapp.model.remote.data.Room
import com.example.chatapp.ui.add_room.AddRoomActivity
import com.example.chatapp.ui.roomDetails.RoomDetailsActivity

class HomeFragment:BaseFragment<ActivityHomeBinding,HomeViewModel>(),Navigator {
    lateinit var adapter :HomeAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingFragment.vm = viewModelFragment
        viewModelFragment.navigator = this
        viewModelFragment.getRoomsList()

        initRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        observeToLiveData()
        adapter.onItemClickListener = object :HomeAdapter.OnItemClickListener{
            override fun onItemClick(room: Room) {
//                var intent = Intent(this@HomeActivity, RoomDetailsActivity::class.java)
//                intent.putExtra("room",room)
//                startActivity(intent)

                //todo:replace intent with action and replace put extra with safe args to navigate and send data from fragment to another
                val action = HomeFragmentDirections.actionHomeFragmentToRoomDetailsFragment(room)
                findNavController().navigate(action)
            }
        }
    }

    private fun observeToLiveData() {
        viewModelFragment.roomsListLiveData.observe(this){
            adapter.changeData(it)
        }
    }

    private fun initRecyclerView() {
        adapter = HomeAdapter(listOf())
        bindingFragment.homeRecyclerView.adapter = adapter
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initializeViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun goToAddRoomActivity() {
        //startActivity(Intent(this, AddRoomActivity::class.java))
        //todo:replace intent with action to navigate from fragment to another
        findNavController().navigate(R.id.action_homeFragment_to_addRoomFragment)
    }
}