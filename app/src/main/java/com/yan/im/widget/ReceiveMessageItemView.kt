package com.yan.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import com.yan.im.R
import kotlinx.android.synthetic.main.view_receive_message_item.view.*
import java.util.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/27 15:20
 *  @description : 收到信息条目view
 */
class ReceiveMessageItemView : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_receive_message_item, this)
    }

    fun bindView(emMessage: EMMessage) {
        updateTimestamp(emMessage)
        updateMessage(emMessage)
    }

    private fun updateMessage(emMessage: EMMessage) {
        if ((emMessage.type == EMMessage.Type.TXT)) {
            receiveMessage.text = (emMessage.body as EMTextMessageBody).message
        } else {
            receiveMessage.text = context.getString(R.string.no_text_message)
        }
    }

    private fun updateTimestamp(emMessage: EMMessage) {
        val msgTime = emMessage.msgTime
        timestamp.text = DateUtils.getTimestampString(Date(msgTime))
    }
}