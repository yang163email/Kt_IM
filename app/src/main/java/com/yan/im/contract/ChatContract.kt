package com.yan.im.contract

import com.hyphenate.chat.EMMessage
import com.yan.im.presenter.BasePresenter

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/27 15:28
 *  @description : 聊天界面协议
 */
interface ChatContract {
    interface Presenter: BasePresenter {
        fun sendMessage(contact: String, message: String)
        fun addMessage(username: String, p0: MutableList<EMMessage>?)
    }

    interface View {
        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
    }
}