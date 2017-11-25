package com.yan.im.utils

import android.support.v4.app.Fragment
import com.yan.im.R
import com.yan.im.ui.fragment.ContactsFragment
import com.yan.im.ui.fragment.ConversationFragment
import com.yan.im.ui.fragment.DynamicFragment

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 11:37
 *  @description : 主界面fragment工厂类
 */
class FragmentFactory private constructor(){

    companion object {
        val instance = FragmentFactory()
    }

    private val conversationFragment by lazy { ConversationFragment() }
    private val contactsFragment by lazy { ContactsFragment() }
    private val dynamicFragment by lazy { DynamicFragment() }

    fun getFragment(tabId: Int): Fragment? {
        return when(tabId) {
            R.id.tab_conversation -> conversationFragment
            R.id.tab_contacts -> contactsFragment
            R.id.tab_dynamic -> dynamicFragment
            else -> null
        }
    }

}