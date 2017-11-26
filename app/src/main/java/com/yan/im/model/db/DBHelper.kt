package com.yan.im.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.yan.im.app.IMApplication
import org.jetbrains.anko.db.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/26 14:03
 *  @description : 数据库helper类
 */
class DBHelper(ctx: Context = IMApplication.instance)
    : ManagedSQLiteOpenHelper(ctx, NAME, null, VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(ContactTable.NAME,
                true,
                ContactTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                ContactTable.CONTACT to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(ContactTable.NAME, true)
        onCreate(db)
    }

    companion object {
        val NAME = "im.db"
        val VERSION = 1
    }
}