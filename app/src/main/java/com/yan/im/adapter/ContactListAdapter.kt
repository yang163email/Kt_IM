package com.yan.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.yan.im.model.ContactListItem
import com.yan.im.ui.activity.ChatActivity
import com.yan.im.widget.ContactListItemView
import org.jetbrains.anko.startActivity

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 17:14
 *  @description : 联系人列表的adapter
 */
class ContactListAdapter(val context: Context, private val contactList: MutableList<ContactListItem>): RecyclerView.Adapter<ContactListAdapter.ContactListItemViewHolder>() {

    override fun getItemCount(): Int = contactList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ContactListItemViewHolder {
        val itemView = ContactListItemView(context)
        return ContactListItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactListItemViewHolder, position: Int) {
        val itemView = holder.itemView as ContactListItemView
        itemView.bindView(contactList[position])
        val username = contactList[position].name
        //条目点击事件、跳转聊天界面
        itemView.setOnClickListener { context.startActivity<ChatActivity>("username" to username) }
    }

    class ContactListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}