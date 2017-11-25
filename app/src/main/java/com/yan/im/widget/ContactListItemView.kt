package com.yan.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.yan.im.R
import com.yan.im.model.ContactListItem
import kotlinx.android.synthetic.main.view_contact_item.view.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 17:04
 *  @description : 联系人列表条目view
 */
class ContactListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_contact_item, this)
    }

    fun bindView(contactListItem: ContactListItem) {
        firstLetter.text = contactListItem.firstLetter.toString()
        userName.text = contactListItem.name
    }
}