package com.yan.im.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.hyphenate.chat.EMClient
import com.yan.im.contract.AddFriendContract

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/26 12:38
 *  @description : 添加好友 P 层
 */
class AddFriendPresenter(val view: AddFriendContract.View): AddFriendContract.Presenter {
    override fun search(key: String) {
        val query = BmobQuery<BmobUser>()
        query.addWhereContains("username", key)
                .addWhereNotEqualTo("username", EMClient.getInstance().currentUser)
        query.findObjects(object : FindListener<BmobUser>() {
            override fun done(p0: MutableList<BmobUser>?, p1: BmobException?) {
                if (p1 == null) view.onSearchSuccess()
                else view.onSearchFailed()
            }
        })
    }
}