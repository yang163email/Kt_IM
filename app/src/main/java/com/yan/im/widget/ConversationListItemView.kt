package com.yan.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import com.yan.im.R
import kotlinx.android.synthetic.main.view_conversation_item.view.*
import java.util.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/27 21:19
 *  @description : 会话界面RecyclerView的条目view
 */
class ConversationListItemView: RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_conversation_item, this)
    }

    fun bindView(emConversation: EMConversation) {
        userName.text = emConversation.conversationId()
        if (emConversation.lastMessage.type == EMMessage.Type.TXT) {
            //如果最后一条下次是文本格式
            val body = emConversation.lastMessage.body as EMTextMessageBody
            lastMessage.text = body.message
        } else {
            lastMessage.text = context.getString(R.string.no_text_message)
        }

        val lastMsgTime = emConversation.lastMessage.msgTime
        timestamp.text = DateUtils.getTimestampString(Date(lastMsgTime))

        if (emConversation.unreadMsgCount > 0) {
            unreadCount.text = emConversation.unreadMsgCount.toString()
            unreadCount.visibility = View.VISIBLE
        } else unreadCount.visibility = View.GONE
    }
}