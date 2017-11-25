package com.yan.im.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.yan.im.contract.ContactContract
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 17:31
 *  @description : 联系人 P 层
 */
class ContactPresenter(val view: ContactContract.View): ContactContract.Presenter {
    override fun loadContacts() {
        doAsync {
            try {
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                uiThread { view.loadContactsSuccess() }
            } catch (e: HyphenateException) {
                //获取列表失败
                uiThread { view.loadContactsFailed() }
            }
        }
    }
}