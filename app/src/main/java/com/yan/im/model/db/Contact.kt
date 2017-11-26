package com.yan.im.model.db

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/26 14:34
 *  @description : 联系人实体类
 */
class Contact(val map: MutableMap<String, Any?>) {

    val _id by map
    val name by map
}