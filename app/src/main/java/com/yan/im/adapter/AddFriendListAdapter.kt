package com.yan.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.yan.im.model.data.AddFriendItem
import com.yan.im.widget.AddFriendListItemView

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/26 12:23
 *  @description : 添加好友RecyclerView的 adapter
 */
class AddFriendListAdapter(val context: Context, private val addFriendItems: MutableList<AddFriendItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val itemView = holder?.itemView as AddFriendListItemView
        itemView.bindView(addFriendItems[position])
    }

    override fun getItemCount(): Int = addFriendItems.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return AddFriendListItemViewHolder(AddFriendListItemView(context))
    }

    class AddFriendListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}