package com.example.daohang.ui.blank

//// ChatAdapter.kt
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.daohang.R
//
//class ChatAdapter(private val messages: List<ChatMessage>) :
//    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
//
//        val layoutRes = when (viewType) {
//            0 -> R.layout.user_message_item
//            1 -> R.layout.system_message_item
//            else -> R.layout.user_message_item
//        }
//        val itemView = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
//        return ChatViewHolder(itemView)
//    }
//
//    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        // 初始化视图元素
//        val messageText1 = itemView.findViewById<TextView>(R.id.messageTextView1)
//        val messageText2 = itemView.findViewById<TextView>(R.id.messageTextView2)
//        val userAvatarImageView: ImageView = itemView.findViewById(R.id.userAvatarImageView)
//        val systemAvatarImageView: ImageView = itemView.findViewById(R.id.systemAvatarImageView)
//    }
//
//    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
//        val message = messages[position]
////        holder.messageText.text = message.text
//
//        // 根据消息类型设置头像图标
//        if (message.messageType == MessageType.USER) {
//            // 用户消息
//            holder.userAvatarImageView.visibility = View.VISIBLE
//            holder.systemAvatarImageView.visibility = View.GONE
//            // 设置用户头像资源
//            holder.userAvatarImageView.setImageResource(R.drawable.user)
//            holder.messageText1.text = message.text
//        } else if (message.messageType == MessageType.SYSTEM) {
//            // 系统消息
//            holder.userAvatarImageView.visibility = View.GONE
//            holder.systemAvatarImageView.visibility = View.VISIBLE
//            // 设置系统头像资源
//            holder.systemAvatarImageView.setImageResource(R.drawable.robot)
//            holder.messageText2.text = message.text
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return messages.size
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return messages[position].messageType.ordinal
//    }
//}
//
//
//

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.daohang.R


class ChatAdapter(private val messages: List<ChatMessage>) :
    RecyclerView.Adapter<ChatAdapter.MessageViewHolder>() {

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImageView: ImageView = itemView.findViewById(R.id.avatarImageView)
        val messageTextView: TextView = itemView.findViewById(R.id.messageTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.avatarImageView.setImageResource(
            if (message.isUser) R.drawable.user else R.drawable.robot
        )
        holder.messageTextView.text = message.text
    }

    override fun getItemCount() = messages.size
}