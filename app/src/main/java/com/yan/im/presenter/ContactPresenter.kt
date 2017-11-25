package com.yan.im.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.yan.im.contract.ContactContract
import com.yan.im.model.ContactListItem
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 17:31
 *  @description : 联系人 P 层
 */
class ContactPresenter(val view: ContactContract.View): ContactContract.Presenter {
    val contactList = mutableListOf<ContactListItem>()

    override fun loadContacts() {
        doAsync {
            try {
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                //先根据首字母进行排序
                usernames.sortBy { it[0] }
                usernames.forEach {
                    //遍历获取需要的数据
                    val contactListItem = ContactListItem(it, it[0].toUpperCase())
                    contactList.add(contactListItem)
                }
                uiThread { view.loadContactsSuccess(contactList) }
            } catch (e: HyphenateException) {
                //获取列表失败
                uiThread { view.loadContactsFailed() }
            }
        }
    }
}