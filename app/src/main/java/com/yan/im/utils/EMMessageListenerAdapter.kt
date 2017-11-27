package com.yan.im.utils

import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMMessage

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/27 18:06
 *  @description : 环信消息监听适配器
 */
open class EMMessageListenerAdapter: EMMessageListener {
    override fun onMessageRecalled(p0: MutableList<EMMessage>?) {
    }

    override fun onMessageChanged(p0: EMMessage?, p1: Any?) {
    }

    override fun onCmdMessageReceived(p0: MutableList<EMMessage>?) {
    }

    override fun onMessageReceived(p0: MutableList<EMMessage>?) {
    }

    override fun onMessageDelivered(p0: MutableList<EMMessage>?) {
    }

    override fun onMessageRead(p0: MutableList<EMMessage>?) {
    }
}