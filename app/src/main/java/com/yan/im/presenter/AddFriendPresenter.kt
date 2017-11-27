package com.yan.im.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.hyphenate.chat.EMClient
import com.yan.im.contract.AddFriendContract
import com.yan.im.model.data.AddFriendItem
import com.yan.im.model.db.IMDatabase
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/26 12:38
 *  @description : 添加好友 P 层
 */
class AddFriendPresenter(val view: AddFriendContract.View): AddFriendContract.Presenter {

    val addFriendItems = mutableListOf<AddFriendItem>()

    override fun search(key: String) {
        addFriendItems.clear()
        val query = BmobQuery<BmobUser>()
        query.addWhereContains("username", key)
                .addWhereNotEqualTo("username", EMClient.getInstance().currentUser)
        query.findObjects(object : FindListener<BmobUser>() {
            override fun done(p0: MutableList<BmobUser>?, p1: BmobException?) {
                if (p1 == null) {
                    doAsync {
                        //先查询数据库
                        val allContacts = IMDatabase.instance.getAllContacts()
                        p0?.forEach {
                            var isAdded = false
                            allContacts.forEach { contact->
                                //通过遍历查询到的数据，与获取到的数据进行对比
                                if (contact.name == it.username) isAdded = true
                            }
                            val addFriendItem = AddFriendItem(it.username, it.createdAt, isAdded)
                            addFriendItems.add(addFriendItem)
                        }
                        uiThread { view.onSearchSuccess() }
                    }
                }
                else view.onSearchFailed()
            }
        })
    }
}