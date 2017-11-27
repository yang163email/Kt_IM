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
}