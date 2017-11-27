package com.yan.im.contract

import com.yan.im.presenter.BasePresenter

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/27 15:28
 *  @description : 聊天界面协议
 */
interface ChatContract {
    interface Presenter: BasePresenter {
        fun sendMessage(contact: String, message: String)
    }

    interface View {
        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
    }
}