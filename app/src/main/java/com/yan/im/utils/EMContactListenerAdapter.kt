package com.yan.im.utils

import com.hyphenate.EMContactListener

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/26 10:03
 *  @description : 环信联系人监听适配器
 */
open class EMContactListenerAdapter: EMContactListener {
    override fun onContactInvited(p0: String?, p1: String?) {

    }

    override fun onContactDeleted(p0: String?) {
    }

    override fun onFriendRequestAccepted(p0: String?) {
    }

    override fun onContactAdded(p0: String?) {
    }

    override fun onFriendRequestDeclined(p0: String?) {
    }
}