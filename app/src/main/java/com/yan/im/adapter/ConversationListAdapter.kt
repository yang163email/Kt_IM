package com.yan.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hyphenate.chat.EMConversation
import com.yan.im.ui.activity.ChatActivity
import com.yan.im.widget.ConversationListItemView
import org.jetbrains.anko.startActivity

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/27 21:23
 *  @description : 消息界面RecyclerView的adapter
 */
class ConversationListAdapter(val context: Context, val conversations: MutableList<EMConversation>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = conversations.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val conversationListItemView = holder?.itemView as ConversationListItemView
        conversationListItemView.bindView(conversations[position])
        conversationListItemView.setOnClickListener {
            context.startActivity<ChatActivity>("username" to conversations[position].conversationId())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ConversationListItemViewHolder(ConversationListItemView(context))
    }

    class ConversationListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}