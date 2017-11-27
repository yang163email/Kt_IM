package com.yan.im.presenter

import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.yan.im.contract.ContactContract
import com.yan.im.model.data.ContactListItem
import com.yan.im.model.db.Contact
import com.yan.im.model.db.IMDatabase
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
            //加载前，先清空之前的联系人列表
            contactList.clear()
            //加载前，先删除数据库联系人
            IMDatabase.instance.deleteAllContacts()
            try {
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                //先根据首字母进行排序
                usernames.sortBy { it[0] }
                usernames.forEachIndexed { index, s ->
                    //遍历获取需要的数据
                    //判断是佛显示字母。如果是第一个、或者当前与上一个相同，则为true
                    val showFirstLetter = index == 0 || s[0] != usernames[index-1][0]
                    val contactListItem = ContactListItem(s, s[0].toUpperCase(), showFirstLetter)
                    contactList.add(contactListItem)

                    //保存联系人到数据库
                    val contact = Contact(mutableMapOf("name" to s))
                    IMDatabase.instance.saveContact(contact)
                }
                uiThread { view.loadContactsSuccess(contactList) }
            } catch (e: HyphenateException) {
                //获取列表失败
                uiThread { view.loadContactsFailed() }
            }
        }
    }
}