package com.yan.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hyphenate.chat.EMMessage
import com.hyphenate.util.DateUtils
import com.yan.im.widget.ReceiveMessageItemView
import com.yan.im.widget.SendMessageItemView

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/27 16:14
 *  @description : 聊天界面RecyclerView的adapter
 */
class MessageListAdapter(val context: Context, val messages: MutableList<EMMessage>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val ITEM_TYPE_SEND_MESSAGE = 0
        val ITEM_TYPE_RECEIVE_MESSAGE = 1
    }

    override fun getItemViewType(position: Int): Int =
        if (messages[position].direct() == EMMessage.Direct.SEND)
            ITEM_TYPE_SEND_MESSAGE
        else ITEM_TYPE_RECEIVE_MESSAGE

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val showTimestamp = isShowTimestamp(position)
        if (getItemViewType(position) == ITEM_TYPE_SEND_MESSAGE) {
            val sendMessageItemView = holder?.itemView as SendMessageItemView
            sendMessageItemView.bindView(messages[position], showTimestamp)
        } else {
            val receiveMessageItemView = holder?.itemView as ReceiveMessageItemView
            receiveMessageItemView.bindView(messages[position], showTimestamp)
        }
    }

    /**
     * 是否显示时间戳
     */
    private fun isShowTimestamp(position: Int): Boolean {
        var showTimestamp = true
        if (position > 0) {
            showTimestamp = !DateUtils.isCloseEnough(messages[position-1].msgTime, messages[position].msgTime)
        }
        return showTimestamp
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == ITEM_TYPE_SEND_MESSAGE) SendMessageViewHolder(SendMessageItemView(context))
        else ReceiveMessageViewHolder(ReceiveMessageItemView(context))

    override fun getItemCount(): Int = messages.size

    class SendMessageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    class ReceiveMessageViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}