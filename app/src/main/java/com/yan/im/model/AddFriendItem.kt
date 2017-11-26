package com.yan.im.model

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/26 13:43
 *  @description : 添加好友数据bean
 */
data class AddFriendItem(val username: String, val timestamp: String, val isAdded: Boolean = false)