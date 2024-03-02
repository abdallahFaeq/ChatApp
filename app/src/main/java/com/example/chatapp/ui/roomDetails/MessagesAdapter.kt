package com.example.chatapp.ui.roomDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.chatapp.R
import com.example.chatapp.databinding.RecieverRowBinding
import com.example.chatapp.databinding.SenderRowBinding
import com.example.chatapp.model.remote.data.Message
import com.example.chatapp.utils.Data

class MessagesAdapter(var messages:MutableList<Message>):Adapter<MessagesAdapter.MessageViewHolder>() {

    val SENDER_MESSAGE = 0
    val RECIEVER_MESSAGE = 1
    override fun getItemViewType(position: Int): Int {
        if (messages.get(position).senderId == Data.user?.id){
            return SENDER_MESSAGE
        }else{
            return RECIEVER_MESSAGE
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessagesAdapter.MessageViewHolder {
        var holder : MessageViewHolder?=null
        if (viewType == SENDER_MESSAGE){
            var SenderBinding = DataBindingUtil.inflate<SenderRowBinding>(
                LayoutInflater.from(parent.context),
                R.layout.sender_row,
                parent,
                false
            )
            holder = SenderViewHolder(SenderBinding)
        }else if (viewType == RECIEVER_MESSAGE){
            var RecieverBinding = DataBindingUtil.inflate<RecieverRowBinding>(
                LayoutInflater.from(parent.context),
                R.layout.reciever_row,
                parent,
                false
            )
             holder = RecieverViewHolder(RecieverBinding)
        }
        return holder!!
    }

    override fun onBindViewHolder(holder: MessagesAdapter.MessageViewHolder, position: Int) {
        var message = messages.get(position)

        var viewType = getItemViewType(position)
        if (viewType == SENDER_MESSAGE){
            var senderHolder = holder as SenderViewHolder
            senderHolder.bind(message)
        }else if (viewType == RECIEVER_MESSAGE){
            var recieverHolder = holder as RecieverViewHolder
            recieverHolder.bind(message)
        }

    }

    override fun getItemCount(): Int {
        return messages.size
    }

    fun changeData(messages:MutableList<Message>){
        this.messages.addAll(messages)
        notifyItemRangeInserted(this.messages.size,messages.size)
    }

    open inner class MessageViewHolder(view:View):ViewHolder(view){

    }
    inner class SenderViewHolder(var binding:SenderRowBinding):MessageViewHolder(binding.root){
         fun bind(message:Message) {
            binding.mess = message
        }

    }
    inner class RecieverViewHolder(var binding:RecieverRowBinding):MessageViewHolder(binding.root){
         fun bind(message:Message) {
            binding.message = message
        }

    }
}