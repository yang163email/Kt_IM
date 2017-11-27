package com.yan.im.presenter

import com.yan.im.contract.ChatContract

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/27 15:47
 *  @description : 聊天界面 P 层
 */
class ChatPresenter(val view: ChatContract.View): ChatContract.Presenter {
    override fun sendMessage(contact: String, message: String) {

    }
}