package com.yan.im.model.data

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 17:48
 *  @description : 联系人数据模型
 */
data class ContactListItem(val name: String,
                           val firstLetter: Char,
                           val showFirstLetter: Boolean = true)