package com.yan.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.yan.im.widget.AddFriendListItemView

/**
 *  @author      : 楠GG
 *  @date        : 2017/11/26 12:23
 *  @description : 添加好友RecyclerView的 adapter
 */
class AddFriendListAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

    }

    override fun getItemCount(): Int = 30

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return AddFriendListItemViewHolder(AddFriendListItemView(context))
    }

    class AddFriendListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}