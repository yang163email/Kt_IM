package com.yan.im.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.yan.im.widget.ContactListItemView

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/25 17:14
 *  @description : 联系人列表的adapter
 */
class ContactListAdapter: RecyclerView.Adapter<ContactListAdapter.ContactListItemViewHolder>() {
    override fun getItemCount(): Int = 30

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ContactListItemViewHolder {
        val itemView = ContactListItemView(parent?.context)
        return ContactListItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactListItemViewHolder?, position: Int) {
    }

    class ContactListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}