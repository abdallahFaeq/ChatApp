package com.example.chatapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.chatapp.R
import com.example.chatapp.databinding.RoomRowBinding
import com.example.chatapp.model.remote.data.Room

class HomeAdapter(var rooms:List<Room>):Adapter<HomeAdapter.HomeHolder>() {
    inner class HomeHolder(var binding:RoomRowBinding):ViewHolder(binding.root){
        fun bind(room:Room){
            binding.room = room
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        var roomRowBinding = DataBindingUtil.inflate<RoomRowBinding>(
            LayoutInflater.from(parent.context),
            R.layout.room_row,
            parent,
            false
        )
        return HomeHolder(roomRowBinding)
    }

    override fun getItemCount(): Int {
     return rooms.size
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        var room = rooms[position]
        holder.bind(room)
        if (onItemClickListener != null){
            holder.itemView.setOnClickListener({
                onItemClickListener?.onItemClick(room)
            })
        }

    }
    fun changeData(rooms:List<Room>){
        this.rooms = rooms
        notifyDataSetChanged()
    }

    var onItemClickListener:OnItemClickListener?=null
    interface OnItemClickListener{
        fun onItemClick(room:Room)
    }
}