package com.yan.im.model.db

import com.yan.im.ext.toVarargArray
import org.jetbrains.anko.db.insert

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/26 19:32
 *  @description : 联系人数据库
 */
class IMDatabase {
    companion object {
        val dbHelper = DBHelper()
        val instance = IMDatabase()
    }

    fun saveContact(contact: Contact) {
        dbHelper.use {
            insert(ContactTable.NAME, *contact.map.toVarargArray())
        }
    }
}