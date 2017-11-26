package com.yan.im.model.db

import com.yan.im.ext.toVarargArray
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

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

    /**
     * 保存联系人
     */
    fun saveContact(contact: Contact) {
        dbHelper.use {
            insert(ContactTable.NAME, *contact.map.toVarargArray())
        }
    }

    /**
     * 获取所有联系人
     */
    fun getAllContacts(): List<Contact> = dbHelper.use {
            select(ContactTable.NAME).parseList(object : MapRowParser<Contact>{
                override fun parseRow(columns: Map<String, Any?>): Contact = Contact(columns.toMutableMap())
            })
        }

    /**
     * 删除所有联系人
     */
    fun deleteAllContacts() {
        dbHelper.use {
            delete(ContactTable.NAME, null, null)
        }
    }
}