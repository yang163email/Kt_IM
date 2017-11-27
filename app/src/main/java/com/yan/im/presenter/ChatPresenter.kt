package com.yan.im.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.yan.im.contract.ChatContract
import com.yan.im.utils.EMCallBackAdapter

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/27 15:47
 *  @description : 聊天界面 P 层
 */
class ChatPresenter(val view: ChatContract.View): ChatContract.Presenter {

    val messages = mutableListOf<EMMessage>()

    override fun sendMessage(contact: String, message: String) {
        val emMessage = EMMessage.createTxtSendMessage(message, contact)
        emMessage.setMessageStatusCallback(object : EMCallBackAdapter{
            override fun onSuccess() {
                uiThread { view.onSendMessageSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread { view.onSendMessageFailed() }
            }

        })
        messages.add(emMessage)
        view.onStartSendMessage()
        EMClient.getInstance().chatManager().sendMessage(emMessage)
    }

    override fun addMessage(username: String, p0: MutableList<EMMessage>?) {
        //加入当前的消息列表
        p0?.let { messages.addAll(it) }
        //更新消息为已读消息
        //获取跟联系人的会话，然后标记会话里面的消息为全部已读
        val conversation = EMClient.getInstance().chatManager().getConversation(username)
        conversation.markAllMessagesAsRead()
    }

}