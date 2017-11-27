package com.yan.im.widget

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import com.yan.im.R
import kotlinx.android.synthetic.main.view_send_message_item.view.*
import java.util.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/27 15:20
 *  @description : 发送信息条目view
 */
class SendMessageItemView: RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_send_message_item, this)
    }

    fun bindView(emMessage: EMMessage, showTimestamp: Boolean) {
        updateTimestamp(emMessage, showTimestamp)
        updateMessage(emMessage)
        updateProgress(emMessage)
    }

    /**
     * 更新加载图片
     */
    private fun updateProgress(emMessage: EMMessage) {
        emMessage.status().let {
            when (it) {
                EMMessage.Status.INPROGRESS -> {
                    sendMessageProgress.visibility = View.VISIBLE
                    sendMessageProgress.setImageResource(R.drawable.send_message_progress)
                    val animationDrawable = sendMessageProgress.drawable as AnimationDrawable
                    animationDrawable.start()
                }
                EMMessage.Status.SUCCESS -> sendMessageProgress.visibility = View.GONE
                EMMessage.Status.FAIL -> {
                    sendMessageProgress.visibility = View.VISIBLE
                    sendMessageProgress.setImageResource(R.mipmap.msg_error)
                }
            }
        }
    }

    /**
     * 更新消息
     */
    private fun updateMessage(emMessage: EMMessage) {
        if ((emMessage.type == EMMessage.Type.TXT)) {
            sendMessage.text = (emMessage.body as EMTextMessageBody).message
        } else {
            sendMessage.text = context.getString(R.string.no_text_message)
        }
    }

    /**
     * 更新时间戳
     */
    private fun updateTimestamp(emMessage: EMMessage, showTimestamp: Boolean) {
        if (showTimestamp) {
            //如果需要显示，则将时间戳显示出来
            val msgTime = emMessage.msgTime
            timestamp.text = DateUtils.getTimestampString(Date(msgTime))
            timestamp.visibility = View.VISIBLE
        } else timestamp.visibility = View.GONE
    }
}